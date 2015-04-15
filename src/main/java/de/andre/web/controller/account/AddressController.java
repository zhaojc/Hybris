package de.andre.web.controller.account;

import de.andre.entity.core.DpsAddress;
import de.andre.service.account.AddressCardsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

/**
 * Created by andreika on 3/29/2015.
 */

@RestController
public class AddressController {

	private final AddressCardsTools addressCardsTools;

	@Autowired
	public AddressController(AddressCardsTools addressCardsTools) {
		this.addressCardsTools = addressCardsTools;
	}

	@RequestMapping(value = "/address/getEditAddress", method = RequestMethod.GET)
	public DpsAddress getEditAddress(@RequestParam("addressId") String addressId) {
		DpsAddress dpsAddress = addressCardsTools.getAddressById(addressId);
		return dpsAddress;
	}

	@RequestMapping(value = "/address/deleteAddress", method = RequestMethod.POST)
	public Response deleteAddress(@RequestParam("addressId") String addressId) {
		DpsAddress dpsAddress = addressCardsTools.getAddressById(addressId);
		return Response.ok().build();
	}
}
