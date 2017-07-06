package com.SpringMVC.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.model.CodeMaster;
import com.SpringMVC.uriconstant.CodeMasterRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class CodeMasterController {

	@Autowired
    private CodeMasterDAO codeMasterDAO;

    @RequestMapping(value = CodeMasterRestURIConstant.Get, method = RequestMethod.GET)
	public String getCode(@PathVariable String codeid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(codeMasterDAO.get(codeid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CodeMasterRestURIConstant.GetType, method = RequestMethod.GET)
	public String getType(@PathVariable String codetype) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(codeMasterDAO.list(codetype));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CodeMasterRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<CodeMaster> createCodeMaster(@RequestBody CodeMaster codeMaster) throws IOException {
    	codeMasterDAO.save(codeMaster);
        return new ResponseEntity<CodeMaster>(codeMaster, HttpStatus.CREATED);
    }

    @RequestMapping(value = CodeMasterRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<CodeMaster> updateCodeMaster(@RequestBody CodeMaster codeMaster) {
    	CodeMaster currentCodeMaster = codeMasterDAO.get(codeMaster.getcodeid());
         
        if (currentCodeMaster==null) {
            return new ResponseEntity<CodeMaster>(codeMaster, HttpStatus.NOT_FOUND);
        }
        
        currentCodeMaster.setcodename(codeMaster.getcodename());
        codeMasterDAO.update(currentCodeMaster);
        return new ResponseEntity<CodeMaster>(codeMaster, HttpStatus.OK);
    }

    @RequestMapping(value = CodeMasterRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<CodeMaster> deleteBrand(@RequestBody CodeMaster codeMaster) {
        if (codeMaster == null) {
            return new ResponseEntity<CodeMaster>(codeMaster, HttpStatus.NOT_FOUND);
        }
 
        codeMasterDAO.delete(codeMaster.getcodeid());
        return new ResponseEntity<CodeMaster>(codeMaster, HttpStatus.OK);
    }
}
