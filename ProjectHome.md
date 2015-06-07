# Distributed In-memory database for analytics #

## Features ##
  1. Reversed bit-map indexed for extremely fast scans.
  1. Measure calculations support mathematical expressions and SQL style aggregations e.g. SUM(Volume) etc.
  1. Supports SUM,MIN,MAX,COUNT. More functions to be added soon.
  1. Has a SQL select statement interface which produces [Google DataTable](https://developers.google.com/chart/interactive/docs/dev/dsl_javadocs/com/google/visualization/datasource/datatable/DataTable)
  1. Ability to do what-if analysis using [Scenarios](https://code.google.com/p/bit-dekk/wiki/Scenario) on current data.