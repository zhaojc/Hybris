package de.andre.web.controller.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.andre.entity.core.DpsAddress;
import de.andre.service.account.AddressTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by andreika on 3/29/2015.
 */

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	private final AddressTools addressCardsTools;
	private final ObjectMapper objectMapper;

	@Autowired
	public AddressController(final AddressTools addressCardsTools, final ObjectMapper objectMapper) {
		this.addressCardsTools = addressCardsTools;
		this.objectMapper = objectMapper;
	}

	@RequestMapping(value = "/getEditAddress", method = RequestMethod.GET)
	public DpsAddress getEditAddress(@RequestParam("addressId") final String addressId) {
		final DpsAddress dpsAddress = addressCardsTools.getAddressById(addressId);
		return dpsAddress;
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public ObjectNode deleteAddress(@RequestParam("addressId") final String addressId) {
		try {
			addressCardsTools.deleteAdressById(addressId);
			final ObjectNode response = objectMapper.createObjectNode();
			response.put("success", true);
			response.put("deletedId", addressId);

			return response;
		} catch (Exception e) {
			return objectMapper.createObjectNode().put("success", false).put("err", e.toString());
		}

	}

	@RequestMapping(value = "/modifyAddress", method = RequestMethod.POST)
	public ObjectNode modifyAddress(@Valid final DpsAddress dpsAddress) throws JsonProcessingException {
		try {
			final Integer newAddressId = addressCardsTools.createAddress(dpsAddress);
			return objectMapper.createObjectNode().put("success", true).put("newAddressId", newAddressId);
		} catch (Exception e) {
			return objectMapper.createObjectNode().put("success", false).put("err", e.toString());
		}
	}
}
