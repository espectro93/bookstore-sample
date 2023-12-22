package com.espectro93.examples.springgraphqlbookstore.infrastructure.config;

import com.espectro93.examples.springgraphqlbookstore.infrastructure.error.BadRequestException;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.error.NotFoundException;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GraphQLExceptionConfig implements DataFetcherExceptionResolver {

    @NonNull
    @Override
    public Mono<List<GraphQLError>> resolveException(
        @NonNull Throwable exception,
        DataFetchingEnvironment environment
    ) {
        List<SourceLocation> sourceLocation = List.of(
            environment.getField().getSourceLocation()
        );

        if (
            exception instanceof ConstraintViolationException constraintViolationException
        ) {
            log.info(
                "[GraphQLExceptionHandler] ConstraintViolationException type"
            );
            return Mono.just(
                handleConstraintViolationException(
                    constraintViolationException,
                    sourceLocation
                )
            );
        }

        if (
            exception instanceof IllegalArgumentException illegalArgumentException
        ) {
            log.info("[GraphQLExceptionHandler] illegalArgumentException type");
            var badRequest = new BadRequestException(
                illegalArgumentException.getMessage(),
                sourceLocation
            );
            return Mono.just(Collections.singletonList(badRequest));
        }

        if (exception instanceof NotFoundException notFoundException) {
            log.info("[GraphQLExceptionHandler] NotFoundException type");
            notFoundException.setLocations(sourceLocation);
            return Mono.just(Collections.singletonList(notFoundException));
        }

        return Mono.empty();
    }

    private List<GraphQLError> handleConstraintViolationException(
        ConstraintViolationException exception,
        List<SourceLocation> locations
    ) {
        log.info(
            "[GraphQLExceptionHandler] Creating lis of BadRequestException..."
        );
        return exception
            .getConstraintViolations()
            .stream()
            .map(constraint ->
                new BadRequestException(
                    constraint.getMessageTemplate(),
                    locations
                )
            )
            .map(GraphQLError.class::cast)
            .toList();
    }
}
