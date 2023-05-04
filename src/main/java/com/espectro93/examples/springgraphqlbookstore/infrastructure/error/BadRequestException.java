package com.espectro93.examples.springgraphqlbookstore.infrastructure.error;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BadRequestException
    extends RuntimeException
    implements GraphQLError {

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    private final String message;

    // Below code used for GraphQL only
    private final List<SourceLocation> locations;

    public BadRequestException(String message, List<SourceLocation> locations) {
        this.message = message;
        this.locations = locations;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put("errorCode", STATUS.value());
        return customAttributes;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }
}
