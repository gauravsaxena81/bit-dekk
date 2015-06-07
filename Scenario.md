# What-if analysis and Scenarios #

The following approach is an implementation of technical paper on [New Dimension Value Introduction for in-memory What-If Analysis](http://arxiv.org/abs/1302.0351)

Bit-dekk provides an implementation to help create scenarios on base data. The scenarios are added as dimension values which can then be used in filters and views.

e.g. Consider the following data

|Year|Supplier|Product|Volume|Cost|
|:---|:-------|:------|:-----|:---|
|2011|S1      |P1     |10    |1.0 |
|2011|S1      |P2     |11    |1.4 |
|2011|S2      |P1     |12    |1.1 |
|2011|S2      |P2     |13    |1.5 |

If we want to create a scenario for 2012 with an increase of 10% volume and 10% cost, our table would look like

|Year|Supplier|Product|Volume|Cost|
|:---|:-------|:------|:-----|:---|
|2012|S1      |P1     |11    |1.1 |
|2012|S1      |P2     |12.1  |1.54|
|2012|S2      |P1     |13.2  |1.21|
|2012|S2      |P2     |14.3  |1.65|

Bit-dekk helps do this by an ability to introduce dimension value 2012 for the dimension year.

  * Once the dimension value is created, rows can be assigned to it using queries. For example to create the above table, the query can be {"2011","S1","S2","P1","P2"}.

  * Once the rows are assigned, the change in measures can be recorded as multiplying factors. Thus, in the above example the factor with which the result of the above query will be multiplied is Volume: 1.1 and Cost: 1.1

  * A dimension value can be associated with any number of queries as required to represent its rows. For example the above table can also be created using two queries viz. Q1: {"2011","S1","P1","P2"} and Q2: {{"2011","S2","P1","P2"}}

  * Each query will also need its own factors. This helps to have different factors for the set of rows represented by each query. E.g. in the two queries above Q1 can have factors of Volume: 1.1 and Cost 1.1 and Q2 can have factors of Volume: 1.2 and Cost 1.1. The table will then look like below

|Year|Supplier|Product|Volume|Cost|
|:---|:-------|:------|:-----|:---|
|2012|S1      |P1     |11    |1.1 |
|2012|S1      |P2     |12.1  |1.54|
|2012|S2      |P1     |13.2  |1.44|
|2012|S2      |P2     |14.3  |1.80|

It is also possible to create scenarios over scenarios. For example I can create another Supplier S3 and define its rows for 2012.

|Year|Supplier|Product|Volume|Cost|
|:---|:-------|:------|:-----|:---|
|2012|S3      |P1     |12.1  |1.1 |
|2012|S3      |P2     |13.31 |1.54|

Query for S3 will be {"2012","S1","P1","P2"} and factors will be Volume: 1.1 and Cost: 1