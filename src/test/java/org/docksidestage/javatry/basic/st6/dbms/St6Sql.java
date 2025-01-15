package org.docksidestage.javatry.basic.st6.dbms;

// TODO shiny MySQLとPostgreSQLはSQLなのか？(is-aの関係) by jflute (2025/01/15)
// 抽象クラスの概念を導き出すときのセオリーの一つとして、必要としてない具象クラスも想像してみる。
/**
 * @author shiny
 */
public abstract class St6Sql {

    public abstract String buildPagingQuery(int pageSize, int pageNumber);

    protected int calculateOffset(int pageSize, int pageNumber) {
        return pageSize * (pageNumber - 1);
    }
}
