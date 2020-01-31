package com.infotech.book.ticket.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotech.book.ticket.app.controller.TicketBookingController;
import com.infotech.book.ticket.app.entities.Ticket;
import com.infotech.book.ticket.app.service.TicketBookingService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TicketBookingController.class)
public class ControllerTest {
	
	@Autowired MockMvc mvc;
	
	@MockBean TicketBookingService service;
	
	ObjectMapper o = new ObjectMapper();
	
	@Test
	public void getTicketByIdTest() throws Exception {
		Ticket ticket = new Ticket();
		String ticketJson = o.writeValueAsString(ticket);
		
		when(service.getTicketById(anyInt())).thenReturn(ticket);
		MvcResult result = mvc.perform(get("/api/tickets/ticket/1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
				//.andExpect(ObjectMapper o)
		
		String ticketResult = result.getResponse().getContentAsString();
		
		assertEquals(ticketJson, ticketResult);
		
		
		}
	
	
}
