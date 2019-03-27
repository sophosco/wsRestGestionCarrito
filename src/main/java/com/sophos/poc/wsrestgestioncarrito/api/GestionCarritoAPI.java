package com.sophos.poc.wsrestgestioncarrito.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoActualizarRq;
import com.sophos.poc.wsrestgestioncarrito.model.request.GestionCarritoConsultarRq;
import com.sophos.poc.wsrestgestioncarrito.model.response.GestionCarritoActualizarRs;
import com.sophos.poc.wsrestgestioncarrito.model.response.GestionCarritoConsultarRs;
import com.sophos.poc.wsrestgestioncarrito.service.ActualizarCarritoService;
import com.sophos.poc.wsrestgestioncarrito.service.ConsultarCarritoService;
import com.sophos.poc.wsrestgestioncarrito.service.GestionCarritoSecurityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/gestioncarrito")
public class GestionCarritoAPI {

	@Autowired
	private ActualizarCarritoService actualizarCarrito;
	@Autowired
	private ConsultarCarritoService consultarCarrito;
	@Autowired
	private GestionCarritoSecurityService security;
	
	@SuppressWarnings("unused")
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request; 
	public GestionCarritoAPI(ObjectMapper objectMapper, HttpServletRequest request, GestionCarritoSecurityService security, ConsultarCarritoService consultarCarrito, ActualizarCarritoService actualizarCarrito) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.security = security;
		this.consultarCarrito = consultarCarrito;
		this.actualizarCarrito = actualizarCarrito;
	}
	
	
	@ApiOperation(value = "Servicio encargado de retornar la informacion del carrito de compras asociado a la sesion del usuario", response = GestionCarritoConsultarRs.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transacción Exitosa"),
			@ApiResponse(code = 200, message = "Transacción Exitosa"),
			@ApiResponse(code = 201, message = "Transacción Exitosa"),
			@ApiResponse(code = 202, message = "Transacción Exitosa"),
			@ApiResponse(code = 204, message = "Transacción Exitosa"),
			@ApiResponse(code = 206, message = "Se genero un error de negocio"),
			@ApiResponse(code = 400, message = "Error de autorizacion"),
			@ApiResponse(code = 401, message = "Error de autorizacion"),
			@ApiResponse(code = 403, message = "No esta autorizado para consultar el recurso solicitado"),
			@ApiResponse(code = 404, message = "No se ha encontrado el recurso solicitado"),
			@ApiResponse(code = 405, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 500, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 501, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 503, message = "Ha ocurrido un error en la invocación"),			
			@ApiResponse(code = 600, message = "Datos invalidos"),
			@ApiResponse(code = 601, message = "Número de documento inválido"),
			@ApiResponse(code = 700, message = "Ocurrió un timeout en el backend"),
			@ApiResponse(code = 701, message = "El backend respondió con error"),
			@ApiResponse(code = 800, message = "No existe homologación de campo"),
			@ApiResponse(code = 801, message = "No existe homologación de código de respuesta")			
			})
	@RequestMapping(value="/getcart", 
			produces = { "application/json", "application/xml" }, 
	        consumes = { "application/json", "application/xml" },
	        method=RequestMethod.POST)
	public ResponseEntity<GestionCarritoConsultarRs> consultarCarrito(@ApiParam(value = "Identificador Único con formato de 32 dígitos hexadecimales divididos en guiones: 550e8400-e29b-41d4-a716-446655440000" ,required=true) @RequestHeader(value="X-RqUID", required=true) String xRqUID,@ApiParam(value = "Nemonico de Canal Origen de la Transaccion" ,required=true) @RequestHeader(value="X-Channel", required=true) String xChannel,@ApiParam(value = "IP de origen donde se realiza la invocación de servicio o api" ,required=true) @RequestHeader(value="X-IPAddr", required=true) String xIPAddr,@ApiParam(value = "Sesion o token de autenticación del uso del api" ,required=true) @RequestHeader(value="X-Sesion", required=true) String xSesion,@ApiParam(value = "Request de la solicitud de consulta carrito" ,required=true ) @Valid @RequestBody GestionCarritoConsultarRq rq) {
		GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
		String contentType = request.getContentType();
		String tokenSesion = request.getHeader("X-Sesion");
		if (contentType != null && contentType.contains("application/json") && 
				tokenSesion != null && tokenSesion != "") {
			try {				
				if (security.verifyJwtToken(tokenSesion, rq.getIdSession() )) {
					response = consultarCarrito.getCart(rq.getIdSession());
					return new ResponseEntity<GestionCarritoConsultarRs>(response, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.UNAUTHORIZED);
				}
				}catch (Exception e) {
					System.out.println(e);
					return new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		return new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "Servicio encargado de actualizar la informacion del carrito de compras asociado a la sesion del usuario", response = GestionCarritoConsultarRs.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transacción Exitosa"),
			@ApiResponse(code = 200, message = "Transacción Exitosa"),
			@ApiResponse(code = 201, message = "Transacción Exitosa"),
			@ApiResponse(code = 202, message = "Transacción Exitosa"),
			@ApiResponse(code = 204, message = "Transacción Exitosa"),
			@ApiResponse(code = 206, message = "Se genero un error de negocio"),
			@ApiResponse(code = 400, message = "Error de autorizacion"),
			@ApiResponse(code = 401, message = "Error de autorizacion"),
			@ApiResponse(code = 403, message = "No esta autorizado para consultar el recurso solicitado"),
			@ApiResponse(code = 404, message = "No se ha encontrado el recurso solicitado"),
			@ApiResponse(code = 405, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 500, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 501, message = "Ha ocurrido un error en la invocación"),
			@ApiResponse(code = 503, message = "Ha ocurrido un error en la invocación"),			
			@ApiResponse(code = 600, message = "Datos invalidos"),
			@ApiResponse(code = 601, message = "Número de documento inválido"),
			@ApiResponse(code = 700, message = "Ocurrió un timeout en el backend"),
			@ApiResponse(code = 701, message = "El backend respondió con error"),
			@ApiResponse(code = 800, message = "No existe homologación de campo"),
			@ApiResponse(code = 801, message = "No existe homologación de código de respuesta")			
			})
	@RequestMapping(value="/updatecart", 
			 	produces = { "application/json", "application/xml" }, 
		        consumes = { "application/json", "application/xml" },
		        method=RequestMethod.POST)
	public ResponseEntity<GestionCarritoActualizarRs> actualizarCarrito(@ApiParam(value = "Identificador Único con formato de 32 dígitos hexadecimales divididos en guiones: 550e8400-e29b-41d4-a716-446655440000" ,required=true) @RequestHeader(value="X-RqUID", required=true) String xRqUID,@ApiParam(value = "Nemonico de Canal Origen de la Transaccion" ,required=true) @RequestHeader(value="X-Channel", required=true) String xChannel,@ApiParam(value = "IP de origen donde se realiza la invocación de servicio o api" ,required=true) @RequestHeader(value="X-IPAddr", required=true) String xIPAddr,@ApiParam(value = "Sesion o token de autenticación del uso del api" ,required=true) @RequestHeader(value="X-Sesion", required=true) String xSesion,@ApiParam(value = "Request de solicitud actualizacion carrito" ,required=true )  @Valid @RequestBody GestionCarritoActualizarRq rq) {		
		String contentType = request.getContentType();
		String tokenSesion = request.getHeader("X-Sesion");
		if (contentType != null && contentType.contains("application/json") && 
				tokenSesion != null && tokenSesion != "") {
			try {	
				if (security.verifyJwtToken(tokenSesion, rq.getIdSession())) {
					actualizarCarrito.updateCart(rq.getIdSession(), rq);
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.OK);
				}else {
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.UNAUTHORIZED);
				}
				}catch (Exception e) {
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.UNAUTHORIZED);
	}
}
