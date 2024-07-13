package com.espectro93.examples.springgraphqlbookstore.infrastructure.error;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException
    extends RuntimeException
    implements GraphQLError {

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    @Setter
    private List<SourceLocation> locations;

    public NotFoundException(String message) {
        super(message);
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
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }
}
