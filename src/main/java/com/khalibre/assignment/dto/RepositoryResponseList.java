package com.khalibre.assignment.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "github")
@XmlSeeAlso({ Repository.class })
public class RepositoryResponseList extends ResponseList<Repository> {
    private static final long serialVersionUID = -2844376943998084209L;

}
