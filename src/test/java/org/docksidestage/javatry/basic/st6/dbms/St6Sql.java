package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author shiny
 */
public abstract class St6Sql {

    public abstract String buildPagingQuery(int pageSize, int pageNumber);

    protected int calculateOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }
}
