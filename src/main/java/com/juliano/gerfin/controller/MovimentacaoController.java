package com.juliano.gerfin.controller;

import com.juliano.gerfin.logs.LogType;
import com.juliano.gerfin.logs.Logs;
import com.juliano.gerfin.model.Movimentacao;
import com.juliano.gerfin.service.ContaService;
import com.juliano.gerfin.service.MovimentacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Validated
@RequestMapping({"api/v1/gerfin"})
public class MovimentacaoController {

    @Autowired
    private Logs logs;

    @Autowired
    private MovimentacaoService movimentacaoService;

    private static Logger logger = LoggerFactory.getLogger(ContaController.class);

    @PostMapping("/mov")
    public ResponseEntity<Movimentacao> cadastraMovimentacao(
            HttpServletRequest request,
            @RequestParam(name = "id_conta")  String idConta,
            @RequestBody Movimentacao mov,
            @RequestHeader HttpHeaders headers
    ) throws MissingServletRequestParameterException {
        try {
            if(mov.toString().isEmpty()) {
                throw new RuntimeException("Movimentação Vazia.");
            }
            else {
                var _result = movimentacaoService.insert(mov, idConta);
                var _response = new ResponseEntity<Movimentacao>(_result, HttpStatus.OK);
                logs.logRequest(request, headers, _response, LogType.INFO, HttpStatus.OK.toString());
                return _response;
            }
        } catch (Exception e) {
            var _responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            logs.logRequest(request, headers, _responseEntity, LogType.ERROR, e.getMessage());
            return _responseEntity;
        }
    }

    @PutMapping("/mov")
    public ResponseEntity<Movimentacao> atualizaMovimentacao(
            HttpServletRequest request,
            @RequestParam(name = "id_mov")  String idMov,
            @RequestBody Movimentacao mov,
            @RequestHeader HttpHeaders headers
    ) throws MissingServletRequestParameterException {
        try {
            if(mov.toString().isEmpty()) {
                throw new RuntimeException("Movimentação Vazia.");
            }
            else {
                var _result = movimentacaoService.update(idMov, mov);
                var _response = new ResponseEntity<Movimentacao>(_result, HttpStatus.OK);
                logs.logRequest(request, headers, _response, LogType.INFO, HttpStatus.OK.toString());
                return _response;
            }
        } catch (Exception e) {
            var _responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            logs.logRequest(request, headers, _responseEntity, LogType.ERROR, e.getMessage());
            return _responseEntity;
        }
    }

}