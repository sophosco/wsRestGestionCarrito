package com.sophos.poc.wsrestgestioncarrito.api;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sophos.poc.wsrestgestioncarrito.service.AuditoriaService;
import com.sophos.poc.wsrestgestioncarrito.service.ConsultarCarritoService;
import com.sophos.poc.wsrestgestioncarrito.service.GestionCarritoSecurityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/gestioncarrito")
public class GestionCarritoAPI {

	@Autowired
	private ActualizarCarritoService actualizarCarrito;
	@Autowired
	private ConsultarCarritoService consultarCarrito;
	@Autowired
	private GestionCarritoSecurityService security;
	@Autowired
	private AuditoriaService auditoria;	

	private static final Logger logger = LogManager.getLogger(GestionCarritoAPI.class);
	
	@SuppressWarnings("unused")
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request; 
	public GestionCarritoAPI(ObjectMapper objectMapper, HttpServletRequest request, GestionCarritoSecurityService security, ConsultarCarritoService consultarCarrito, ActualizarCarritoService actualizarCarrito, AuditoriaService auditoria ) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.security = security;
		this.consultarCarrito = consultarCarrito;
		this.actualizarCarrito = actualizarCarrito;
		this.auditoria = auditoria;
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
	public ResponseEntity<GestionCarritoConsultarRs> consultarCarrito(
			@ApiParam(value = "Identificador Único con formato de 32 dígitos hexadecimales divididos en guiones: 550e8400-e29b-41d4-a716-446655440000" ,required=true) 
			@RequestHeader(value="X-RqUID", required=true) String xRqUID,
			@ApiParam(value = "Nemonico de Canal Origen de la Transaccion" ,required=true) 
			@RequestHeader(value="X-Channel", required=true) String xChannel,
			@ApiParam(value = "IP de origen donde se realiza la invocación de servicio o api" ,required=true) 
			@RequestHeader(value="X-IPAddr", required=true) String xIPAddr,
			@ApiParam(value = "Sesion o token de autenticación del uso del api" ,required=true) 
			@RequestHeader(value="X-Sesion", required=true) String xSesion,
			@ApiParam(value = "Bandera para validacion de seguridad" ,required=true) 
    		@RequestHeader(value="X-haveToken", required=false) String xHaveToken,
			@ApiParam(value = "Request de la solicitud de consulta carrito" ,required=true ) 
			@Valid @RequestBody GestionCarritoConsultarRq rq) {
		GestionCarritoConsultarRs response = new GestionCarritoConsultarRs();
		String contentType = request.getContentType();
		String tokenSesion = request.getHeader("X-Sesion");
		String securityValidation = request.getHeader("X-haveToken"); 
		logger.info("GestionCarritoConsultarRq:  contentType= " + contentType + "IdSesion="+rq.getIdSession());
		logger.info("GestionCarritoConsultarRq: " + rq.toString() );
		String statusRs = null;
		if (contentType != null && contentType.contains("application/json") && 
				tokenSesion != null && !tokenSesion.equals("")) {
			try {
				if (( securityValidation != null && securityValidation.equals("false")) ||
						 security.verifyJwtToken(tokenSesion, rq.getIdSession())) { 
					response = consultarCarrito.getCart(rq.getIdSession());
					statusRs = HttpStatus.OK.toString();			
					return new ResponseEntity<GestionCarritoConsultarRs>(response, HttpStatus.OK);
				} else {
					statusRs = HttpStatus.UNAUTHORIZED.toString();
					return new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				logger.error("GestionCarritoConsultarRs ERROR: " + e );
				return new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.INTERNAL_SERVER_ERROR);
			} finally {
				logger.info("GestionCarritoConsultarRs - HttpStatus: "+ statusRs );
				logger.info("GestionCarritoConsultarRs - Response: "+ response.toString() );
			}
		}
		statusRs = HttpStatus.NOT_IMPLEMENTED.toString();
		logger.info("GestionCarritoConsultarRs - HttpStatus: "+ statusRs );
		return  new ResponseEntity<GestionCarritoConsultarRs>(HttpStatus.NOT_IMPLEMENTED);
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
	public ResponseEntity<GestionCarritoActualizarRs> actualizarCarrito(
			@ApiParam(value = "Identificador Único con formato de 32 dígitos hexadecimales divididos en guiones: 550e8400-e29b-41d4-a716-446655440000" ,required=true) 
			@RequestHeader(value="X-RqUID", required=true) String xRqUID,
			@ApiParam(value = "Nemonico de Canal Origen de la Transaccion" ,required=true) 
			@RequestHeader(value="X-Channel", required=true) String xChannel,
			@ApiParam(value = "IP de origen donde se realiza la invocación de servicio o api" ,required=true) 
			@RequestHeader(value="X-IPAddr", required=true) String xIPAddr,
			@ApiParam(value = "Sesion o token de autenticación del uso del api" ,required=true) 
			@RequestHeader(value="X-Sesion", required=true) String xSesion,
			@ApiParam(value = "Bandera para validacion de seguridad" ,required=true) 
    		@RequestHeader(value="X-haveToken", required=false) String xHaveToken,
			@ApiParam(value = "Request de solicitud actualizacion carrito" ,required=true )  
			@Valid @RequestBody GestionCarritoActualizarRq rq) {		
		String contentType = request.getContentType();
		String tokenSesion = request.getHeader("X-Sesion");
		String securityValidation = request.getHeader("X-haveToken"); 
		logger.info("GestionCarritoActualizarRq:  contentType= " + contentType + "IdSesion="+rq.getIdSession());
		logger.info("GestionCarritoActualizarRq: " + rq.toString() );
		String statusRs = null;
		if (contentType != null && contentType.contains("application/json") && 
				tokenSesion != null && !tokenSesion.equals("")) {
			try {	
				if (( securityValidation != null && securityValidation.equals("false")) ||
						 security.verifyJwtToken(tokenSesion, rq.getIdSession())) {
					actualizarCarrito.updateCart(rq.getIdSession(), rq);
					String rqUID =  request.getHeader("X-RqUID");
					String channel =  request.getHeader("X-Channel");
					String ip =  request.getHeader("X-IPAddr");					
					auditoria.sendAudit(rqUID,channel,ip,tokenSesion, rq);
					statusRs = HttpStatus.OK.toString();
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.OK);
				}else {
					statusRs = HttpStatus.UNAUTHORIZED.toString();
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.UNAUTHORIZED);
				}
				}catch (Exception e) {
					logger.error("GestionCarritoActualizarRs ERROR: " + e );
					return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.INTERNAL_SERVER_ERROR);
				}finally {
					logger.info("GestionCarritoActualizarRs - HttpStatus: "+ statusRs );
				}
			}
		statusRs = HttpStatus.NOT_IMPLEMENTED.toString();
		logger.info("GestionCarritoActualizarRs - HttpStatus: "+ statusRs );
		return new ResponseEntity<GestionCarritoActualizarRs>(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@RequestMapping(value="/health", method=RequestMethod.GET)
	public String publishMessage() {
		return "Servicio Activo"+ Calendar.getInstance().getTime().toString();
	}
	
}
