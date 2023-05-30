# WebShop-Group-12

![Screenshot of homepage](doc/image%20dump/Screenshots/Home.webp)

## About

This project was used in the evaluation of IDATA2301 and IDATA2305, in the period of 2023 Winter-Spring.

The task was to create a simple web-shop with a given theme (ours was "CyberPunk Gaming Gear"),
with the following basic requirements:

- Landing page
- Product listing
- Product data is stored in a database and displayed from the database.
- The user has the ability to select product(s)
- A shopping cart if it makes sense for the business case
- A checkout – ability to place an order. The order is registered in the database.

## Getting started

To continue our project, you would have the following pre-requisite for development:
- Java 17 or higher
- Maven (either trough installation with, for example, choco for windows, or using IntelliJ)

And for deployment, we recommend the use of:
- Docker (with swarm enabled)
- Jenkins service running on the deployment server
- RSA Certificate if you want ssl (This can be done with, for example, certbot)

### Development

When used in the default profile (dev),
the application will boot up with some development test products and will change will only happen on the local memory.
This is to ensure
that the application works without the need of production database or the requirement of having a database running.

The website works through templates where we inject the data required from the database.
This will make so every product page has the same configuration just with different values.

### Deployment

#### Our deployment

This deployment relies on the use of docker secret.
This requires docker to be setup with swarm to be able to use the features of external secrets.
This allows for multiple "services" to be running to allow for better scalability.
With the server running, docker and jenkins and having the correct certificate,
you are only to change the following files:

In the [prod-properties](src/main/resources/application-prod.properties) you want the following lines:

```properties
#If you want ssl
server.ssl.enable=true
#The certificate keys if ssl is enabled
server.ssl.certificate=/certs/path/to/your/fullchain.pem
server.ssl.certificate-private-key=/certs/path/to/your/privkey.pem
```

and in the [Compose File](docker-compose.yaml):
```yaml
    volumes:
      - type: bind
        source: /etc/letsencrypt #Change this line to mount the right path for the certificate
        target: /certs #You could also change the target, just be careful to reflect this in the properties file above
```

The last thing you need to do is set up one secret in the swarm.
This secret needs to be called db-password and should contain the database password.
If you want to change the name, you can edit the following in the [compose file](docker-compose.yaml):

```yaml
secrets:
  db-password:
    external: true
    name: db-password #Change this to be your new secret name for the database password
```
This makes it so the database container and website have the exact same password without allowing for others to know it.

#### Some important information

The current running setup for the server creates the test products.
This was because of the database removing the content of the previous push,
without us knowing how we would counter this (Probably through the use of volumes).
If you want to change this,
you can easily do it through the [Dev Initializer](src/main/java/no/ntnu/webshop/group12/webshop/tools/DevInitializer.java) and the [Min Initializer](src/main/java/no/ntnu/webshop/group12/webshop/tools/MinInitializer.java)
by tagging them,
so they don't run in the production.
As an example with the Min, you could do the following:

```java
@Component
@Order(1)
@Profile("!prod")
public class MinInitializer implements ApplicationListener<ApplicationReadyEvent> { 
    //...  
}
```

We also needed to use ipv6 in our solution that is not well-supported in docker swarm
([Source](https://github.com/moby/moby/issues/24379)).
This is why we have the current lines:

```yaml
 ports:
      - target: 8443
        published: 443
        mode: host #Because of poor support with ipv6 on docker swarm, we need to enable it to take over the host ports.
```

This removes the flexibility to have multiple website services running on the same host.
There exists other solution to this like [ipv6Nat](https://github.com/robbertkl/docker-ipv6nat)
but this was a small project that required this.
If you are using ipv4, you can easily remove the mode line within the [Compose File](docker-compose.yaml).