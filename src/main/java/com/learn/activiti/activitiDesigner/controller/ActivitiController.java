package com.learn.activiti.activitiDesigner.controller;

import org.activiti.app.domain.editor.AbstractModel;
import org.activiti.app.model.editor.ModelRepresentation;
import org.activiti.app.rest.editor.ModelsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author ranpengfeng
 * @date 2020/8/10
 */
@RestController
@RequestMapping("/model")
public class ActivitiController {
    @Autowired
    private ModelsResource modelsResource;

    @RequestMapping("/create")
    public void createModelNew(HttpServletRequest request, HttpServletResponse response){
        try{
            String modelName = "modelName"+ LocalDateTime.now();
            String modelKey = "modelKey"+ LocalDateTime.now();
            String description = "description";
            ModelRepresentation modelRepresentation = new ModelRepresentation();
            modelRepresentation.setName(modelName);
            modelRepresentation.setKey(modelKey);
            modelRepresentation.setDescription(description);
            modelRepresentation.setModelType(AbstractModel.MODEL_TYPE_BPMN);
            ModelRepresentation model = modelsResource.createModel(modelRepresentation);
            response.sendRedirect(request.getContextPath() + "/app/editor/index.html#/editor/" + model.getId());
        }catch (Exception e){
        }
    }
}
