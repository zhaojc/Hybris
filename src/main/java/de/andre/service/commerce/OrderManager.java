package de.andre.service.commerce;

import de.andre.entity.core.DpsUser;
import de.andre.entity.order.DcsppOrder;
import de.andre.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderManager {
	private static final Logger log = LoggerFactory.getLogger(OrderManager.class);

	private final OrderRepository orderRepository;
	private final OrderTools orderTools;

	@Autowired
	public OrderManager(final OrderRepository orderRepository, final OrderTools orderTools) {
		this.orderRepository = orderRepository;
		this.orderTools = orderTools;
	}

	@Transactional(readOnly = true)
	public DcsppOrder getUserCurrentOrder(final DpsUser dpsUser) {
		final List<DcsppOrder> orders = orderRepository.getCurrentOrdersByUser(dpsUser);
		if (orders.size() == 0) {
			log.info("No orders for user {}", dpsUser.getUserId());
			return null;
		} else if (orders.size() > 1) {
			log.warn("More than one order for user {}", dpsUser.getUserId());
			return orders.get(0);
		} else {
			return orders.get(0);
		}
	}

	@Transactional
	public void persistOrder(final DcsppOrder pOrder) {
		orderTools.persistOrder(pOrder);
	}

	@Transactional
	public DcsppOrder createOrder(final DpsUser profile) {
		return orderTools.getOrderInstance(profile);
	}
}
