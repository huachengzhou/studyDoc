
### mysql必知会

## union关键字组合查询

```
SELECT vend_id ,prod_id,prod_price FROM products  where prod_price <= 5;
vend_id  prod_id   prod_price
1003	FC	2.5
1002	FU1	3.42
1003	SLING	4.49
1003	TNT1	2.5
```


```
SELECT vend_id , prod_id,prod_price from products WHERE vend_id in(1001,1002);
vend_id  prod_id   prod_price
1003	FC	2.5
1002	FU1	3.42
1003	SLING	4.49
1003	TNT1	2.5
```







































































































































































  ## [回到上一级](../index.md)