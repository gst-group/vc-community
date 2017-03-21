package com.wowtour.jersey.vo;

import com.fasterxml.jackson.databind.node.ContainerNode;

public interface BodyWrapper {
    ContainerNode<?> getBody();

    Boolean validate();
}
