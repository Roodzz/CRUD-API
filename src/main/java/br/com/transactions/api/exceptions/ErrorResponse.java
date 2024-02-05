package br.com.transactions.api.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ErrorResponse {

    private final HttpStatus status;
    private final String uri;
    private final String message;


}
