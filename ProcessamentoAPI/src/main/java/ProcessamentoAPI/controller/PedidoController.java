package ProcessamentoAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import ProcessamentoAPI.service.ProcessamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PedidoController {

	private final ProcessamentoService processamentoService;

}