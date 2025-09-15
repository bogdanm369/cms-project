# Getting Started

### Description

Evolve Payments Service

To be used as a Payments Service for purchasing Products ( Subscriptions ) and make payments 
with Credit Cards or PayPal;

##### How is it organized ?

Evolve-Payments service is using the Evolve-payment-commons and implements it's own specific Products,
the EvolveSubscription;

A product catalog is kept which the client can query and obtain the available subscriptions;

After that it can send the uniqueProductCode along with the payment details.

The service will use the uniqueProductCode along with the payment details in order to make the payment.

##### How does it Work ?

Exposes a REST API which :

- Gives the current product catalog ( product names and prices )
- The client can then call the service to make a payment


### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

