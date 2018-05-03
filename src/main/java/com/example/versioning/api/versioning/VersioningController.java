package com.example.versioning.api.versioning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("")
public class VersioningController {
    private final Logger log = LoggerFactory.getLogger(VersioningController.class);
    private final List<String> VERSIONS = Arrays.asList("v1", "v2", "v3");
    private final List<String> FADEOUT_VERSIONS = Arrays.asList("some-faded-out-version", "faded-out-version-2");

    private final String GONE = "/api/error/gone";
    private final String NOT_FOUND = "/api/error/not-found";
    private final String BAD_REQUEST = "/api/error/bad-request";
    private final String INTERNAL_SERVER_ERROR = "/api/error/internal-server-error";

    private final AntPathMatcher pathMatcher;

    public VersioningController() {
        pathMatcher = new AntPathMatcher();
    }

    private String forwardToPreviousVersion(final String version, final String path) {
        if (Objects.isNull(version) || Objects.isNull(path)) {
            return String.format("forward:%s", INTERNAL_SERVER_ERROR);
        }

        if (FADEOUT_VERSIONS.contains(version)) {
            log.warn(String.format("Faded out version has been called, %s", version));
            return String.format("forward:%s", GONE);
        }

        final int idxOfVersion = VERSIONS.indexOf(version);

        if (idxOfVersion == -1) {
            log.warn(String.format("Invalid version, %s", version));
            return String.format("forward:%s", BAD_REQUEST);
        }

        if (idxOfVersion == 0) {
            log.warn(String.format("Unimplemented API, %s", version));
            return String.format("forward:%s", NOT_FOUND);
        }

        return String.format("forward:/api/%s/%s", VERSIONS.get(idxOfVersion - 1), path);
    }

    @RequestMapping("/api/{version}/**")
    public String forward(final @PathVariable String version,
                         final HttpServletRequest request) {
        return forwardToPreviousVersion(version, pathMatcher.extractPathWithinPattern("/api/{version}/**", request.getRequestURI()));
    }

    @RequestMapping(GONE)
    public ResponseEntity<Void> gone() {
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @RequestMapping(NOT_FOUND)
    public ResponseEntity<Void> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(BAD_REQUEST)
    public ResponseEntity<Void> badRequest() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Void> internalServerError() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
