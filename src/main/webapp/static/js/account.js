$(document).ready(function () {

    function openEditPopUp(address) {
        var addressName = address.split('_')[1];
        var ajaxData = {};
        ajaxData['addressName'] = addressName;
        $.ajax({
            type: 'GET',
            url: '/address/getEditAddress',
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            data: $.param(ajaxData),
            success: function (res) {
                var form = $('#modal_addrForm');
                populateAddrForm(form, res);
            }
        });
    }

    function populateAddrForm(form, addrObj) {
        form.find('#modal_addrCity').val(addrObj.city);
        form.find('#modal_addrPostalCode').val(addrObj.postalCode);
        form.find('#modal_addrAddress').val(addrObj.address);
        form.find('#modal_addrState').val(addrObj.state);
        form.find('#modal_addrName').val(addrObj.addressName);
        form.find('#modal_oldAddressName').val(addrObj.addressName);
    }

    function collectAddrForm(form, ajaxData) {
        ajaxData['addressName'] = form.find('#modal_addrName').val();
        ajaxData['state'] = form.find('#modal_addrState').val();
        ajaxData['city'] = form.find('#modal_addrCity').val();
        ajaxData['postalCode'] = form.find('#modal_addrPostalCode').val();
        ajaxData['address'] = form.find('#modal_addrAddress').val();
        ajaxData['countryCode'] = form.find('#modal_addrCountryCode').val();
        //old address nickname
        ajaxData['oldAddressName'] = form.find('#modal_oldAddressName').val();
    }

    $('#account_addresses').on('click', 'button[name=delete_address]', function () {
        var ajaxData = {};
        ajaxData['addressName'] = this.id.split('_')[1];
        $.ajax({
            type: 'POST',
            url: '/address/deleteAddress',
            dataType: 'json',
            data: $.param(ajaxData),
            success: function (res) {
                if (res.success) {
                    $('#deleteAddress_' + res.deletedName).parent().parent().remove();
                } else {
                    console.log(res.error);
                }
            }
        });
    });

    $('#account_addresses').on('click', 'button[name=edit_address]', function () {
        openEditPopUp(this.id);
    });

    $('#add_address').bind('click', function () {
        var form = $('#modal_addrForm')[0];
        form.reset();
        $('#modal_addressName').val('');
    });

    $('#modal_forgotPass').submit(function (event) {
        event.preventDefault();
        var ajaxData = {};
        ajaxData['email'] = $('#modal_forgotPass').find('#modal_forgot_email').val();

        $.ajax({
            type: 'POST',
            url: '/forgotPassword',
            dataType: 'json',
            data: $.param(ajaxData),
            success: function (res) {
                if (res.success) {


                } else {
                    console.log(res.message);
                }
            }
        });
        $('#modal_forgotPass').modal('hide');
    });

    $('#modal_addrForm').submit(function (event) {
        event.preventDefault();
        var ajaxData = {};
        collectAddrForm($('#modal_addrForm'), ajaxData);
        $.ajax({
            type: 'POST',
            url: '/address/modifyAddress',
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(ajaxData),
            success: function (res) {
                if (res.success && res.isNew) {
                    $('#account_addresses').find('tbody')
                        .append($('<tr>')
                            .append($('<td>')
                                .append(ajaxData['city']))
                            .append($('<td>')
                                .append(res.state))
                            .append($('<td>')
                                .append(ajaxData['address']))
                            .append($('<td>')
                                .append(ajaxData['postalCode']))
                            .append($('<td>')
                                .append($('<button>')
                                    .attr('type', 'button')
                                    .attr('id', 'editAddress_' + res.nickname)
                                    .attr('class', 'btn btn-primary')
                                    .attr('name', 'edit_address')
                                    .attr('data-toggle', 'modal')
                                    .attr('data-target', '#addressModal')
                                    .text('Edit'))
                                .append('&nbsp;')
                                .append($('<button>')
                                    .attr('type', 'button')
                                    .attr('id', 'deleteAddress_' + res.nickname)
                                    .attr('class', 'btn btn-danger')
                                    .attr('name', 'delete_address')
                                    .text('Delete')))
                        );
                } else if (res.success) {
                    var addressRow = $('#account_addresses').find('button[id=editAddress_' + res.nickname + ']').parent().parent();
                    addressRow.find('[data-name="companyName"]').text(ajaxData['companyName']);
                    addressRow.find('[data-name="state"]').text(res.state);
                    addressRow.find('[data-name="city"]').text(ajaxData['city']);
                    addressRow.find('[data-name="postalCode"]').text(ajaxData['postalCode']);
                } else {
                    console.log(res.error);
                }
            }
        });
        $('#addressModal').modal('hide');
    });
});