package com.sophos.poc.wsrestgestioncarrito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.wsrestgestioncarrito.api.GestionCarritoAPI;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDetalleDTO;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoActualizarRq;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoConsultarRq;
import com.sophos.poc.wsrestgestioncarrito.model.response.GestionCarritoConsultarRs;
import com.sophos.poc.wsrestgestioncarrito.service.ActualizarCarritoService;
import com.sophos.poc.wsrestgestioncarrito.service.ConsultarCarritoService;
import com.sophos.poc.wsrestgestioncarrito.service.GestionCarritoSecurityService;

public class GestionCarritoAPIUnitTest {

	private MockMvc mockMvc;
	@Mock
	private ConsultarCarritoService mockConsultar;
	@Mock
	private ActualizarCarritoService mockActualizar;
	@Mock
	private HttpServletRequest mockRequest;	
	@Mock
	private GestionCarritoSecurityService mockSecurity;
	@InjectMocks
	private GestionCarritoAPI apiCOntroller;

	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(apiCOntroller).build();
	}
	
	@Test
	public void test_consultar_carrito_success() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
	    response.setIdSession(sessionKey);
	    CarritoDetalleDTO cartDetail = new CarritoDetalleDTO();
		response.setCart(cartDetail );	    
		GestionCarritoConsultarRq request = new GestionCarritoConsultarRq();
		request.setIdSession(sessionKey);
		
		when(mockRequest.getContentType()).thenReturn("application/json");
		when(mockRequest.getHeader("X-Sesion")).thenReturn(sessionKey);
		when(mockSecurity.verifyJwtToken(sessionKey, sessionKey)).thenReturn(true);		
		when(mockConsultar.getCart(sessionKey)).thenReturn(response);
		
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/getcart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")
	                    .header("X-Channel", "POC")
	                    .header("X-IPAddr", "10.10.10.1")
	                    .header("X-Sesion", sessionKey)
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	    
	    verify(mockConsultar, times(1)).getCart(sessionKey);	  
	    verifyNoMoreInteractions(mockConsultar);
	}
	
	@Test
	public void test_consultar_carrito_invalid_token() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
	    response.setIdSession(sessionKey);
	    CarritoDetalleDTO cartDetail = new CarritoDetalleDTO();
		response.setCart(cartDetail );	    
		GestionCarritoConsultarRq request = new GestionCarritoConsultarRq();
		request.setIdSession(sessionKey);
		
		when(mockRequest.getContentType()).thenReturn("application/json");
		when(mockRequest.getHeader("X-Sesion")).thenReturn(sessionKey);
		when(mockSecurity.verifyJwtToken(sessionKey, sessionKey)).thenReturn(false);		
		when(mockConsultar.getCart(sessionKey)).thenReturn(response);
		
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/getcart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")
	                    .header("X-Channel", "POC")
	                    .header("X-IPAddr", "10.10.10.1")
	                    .header("X-Sesion", sessionKey)
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	    
	    verify(mockConsultar, times(0)).getCart(sessionKey);	  
	    verifyNoMoreInteractions(mockConsultar);
	}
	
	@Test
	public void test_consultar_carrito_invalid_request() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoConsultarRq request = new GestionCarritoConsultarRq();
		request.setIdSession(sessionKey);
	    GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
	    response.setIdSession(sessionKey);
	    CarritoDetalleDTO cartDetail = new CarritoDetalleDTO();
		response.setCart(cartDetail );
	    when(mockConsultar.getCart(sessionKey)).thenReturn(response);

	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/getcart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")	                 
	                    .header("X-Sesion", "123456789")
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is4xxClientError());	
	    verify(mockConsultar, times(0)).getCart(sessionKey);
	    verifyNoMoreInteractions(mockConsultar);
	}
	
	
	
	@Test
	public void test_actualizar_carrito_success() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoActualizarRq request = new GestionCarritoActualizarRq();
	    request.setIdSession(sessionKey);		
		when(mockRequest.getContentType()).thenReturn("application/json");
		when(mockRequest.getHeader("X-Sesion")).thenReturn(sessionKey);
		when(mockSecurity.verifyJwtToken(sessionKey, sessionKey)).thenReturn(true);				
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/updatecart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")
	                    .header("X-Channel", "POC")
	                    .header("X-IPAddr", "10.10.10.1")
	                    .header("X-Sesion", sessionKey)
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	    
	    verify(mockActualizar, times(0)).updateCart(sessionKey, request);	  
	}
	
	@Test
	public void test_actualizar_carrito_invalid_token() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoActualizarRq request = new GestionCarritoActualizarRq();		
	    request.setIdSession(sessionKey);		
		when(mockRequest.getContentType()).thenReturn("application/json");
		when(mockRequest.getHeader("X-Sesion")).thenReturn(sessionKey);
		when(mockSecurity.verifyJwtToken(sessionKey, sessionKey)).thenReturn(false);			
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/updatecart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")
	                    .header("X-Channel", "POC")
	                    .header("X-IPAddr", "10.10.10.1")
	                    .header("X-Sesion", sessionKey)
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	    
	    verify(mockActualizar, times(0)).updateCart(sessionKey, request);	  
	}
	
	@Test
	public void test_actualizar_carrito_invalid_request() throws Exception {
	    String sessionKey= "123456789";
	    GestionCarritoActualizarRq request = new GestionCarritoActualizarRq();		
	    request.setIdSession(sessionKey);		
		when(mockRequest.getContentType()).thenReturn("application/json");
		when(mockRequest.getHeader("X-Sesion")).thenReturn(sessionKey);
		when(mockSecurity.verifyJwtToken(sessionKey, sessionKey)).thenReturn(false);			
	    mockMvc.perform(
	    		MockMvcRequestBuilders.post("/gestioncarrito/updatecart")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .header("X-RqUID", "123456")	                    
	                    .header("X-IPAddr", "10.10.10.1")
	                    .header("X-Sesion", sessionKey)
	                    .content(asJsonString(request)))
	            		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	    
	    verify(mockActualizar, times(0)).updateCart(sessionKey, request);	  
	}
	

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
