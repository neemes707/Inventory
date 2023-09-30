package com.solv.inventory.advice;
import com.solv.inventory.exceptions.BadArgumentException;
import com.solv.inventory.exceptions.ItemNotFoundException;
import com.solv.inventory.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StringBuilder> handleMethodArgNotValidException(MethodArgumentNotValidException ex)
    {
        StringBuilder response =new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
                response.append(error.getDefaultMessage()).append(",");
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadArgumentException.class)
    public ResponseEntity<Map<String,String>> handleBadArgumentException(BadArgumentException ex)
    {
       Map<String,String> response=new HashMap<>();
       response.put("Error:",ex.getLocalizedMessage());
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String,String> response=new HashMap<>();
        response.put("Error",ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleItemNotFoundException(ItemNotFoundException exception){
        Map<String,String> response=new HashMap<>();
        response.put("Error", "No item exists");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StringBuilder> handleGlobalException(Exception ex)
    {
        StringBuilder response=new StringBuilder();
        response.append(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
