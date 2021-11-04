package com.devteam.digital.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@XmlRootElement
public class ErrorMessage {

    /** contains the same HTTP Status code returned by the server */
    @XmlElement(name = "status")
    int status;

    /** application specific error code */
    @XmlElement(name = "code")
    int code;

    /** message describing the error*/
    @XmlElement(name = "message")
    String message;

    /** link point to page where the error message is documented */
    @XmlElement(name = "link")
    String link;

    /** extra information that might useful for developers */
    @XmlElement(name = "developerMessage")
    String developerMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ErrorMessage(AppException ex){
        BeanUtils.copyProperties(ex,this);
        this.status = ex.status.value();
    }

    public ErrorMessage(NoHandlerFoundException ex){
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = ex.getMessage();
        this.link = "www.dev-demo.website";
    }

    public ErrorMessage() {}

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }

    }
}
