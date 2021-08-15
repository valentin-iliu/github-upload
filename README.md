This simple app (Java8-SpringBoot-Maven-H2DB) is based on the following example:
https://spring.io/guides/gs/accessing-data-rest/

In order to run it, go to command prompt and execute "mvnw spring-boot:run"

Once the application started, go to your favourite REST client (e.g. SoapUI etc.) and make http requests as follows:

1. To add a Person make a POST request with the following JSON payload to http://localhost:8080/people

{"firstName": "Jane", "lastName": "Jones", "addresses": [{"street": "Glenfield Road", "postalCode": "12345"},{"street": "Brindley Rd"}]}


2. To list the added Person make a GET request to http://localhost:8080/people

{
  "_embedded" : {
    "people" : [ {
      "firstName" : "Jane",
      "lastName" : "Jones",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/people/1"
        },
        "person" : {
          "href" : "http://localhost:8080/people/1"
        },
        "addresses" : {
          "href" : "http://localhost:8080/people/1/addresses"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/people"
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}

3. To replace the added Person's details make a PUT request to http://localhost:8080/people/1

{"firstName": "Gina", "addresses": [{"street": "Glenfield Road", "postalCode": "12345"},{"street": "Brindley Rd"}]}

Now a a GET request to http://localhost:8080/people should return

{
  "_embedded" : {
    "people" : [ {
      "firstName" : "Gina",
      "lastName" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/people/1"
        },
        "person" : {
          "href" : "http://localhost:8080/people/1"
        },
        "addresses" : {
          "href" : "http://localhost:8080/people/1/addresses"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/people"
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}

4. To update the Person's details make a PATCH request to http://localhost:8080/people/1

{"lastName": "Gibbs"}

Now a a GET request to http://localhost:8080/people should return

{
  "_embedded" : {
    "people" : [ {
      "firstName" : "Gina",
      "lastName" : "Gibbs",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/people/1"
        },
        "person" : {
          "href" : "http://localhost:8080/people/1"
        },
        "addresses" : {
          "href" : "http://localhost:8080/people/1/addresses"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people"
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/people"
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}

5. To add an address to our Person, do a POST request to http://localhost:8080/people/1/addresses/add:

{"street": "Abbey Rd", "city":"London"}

http://localhost:8080/people/1/addresses should now return

{
  "_embedded" : {
    "addresses" : [ {
      "street" : "Glenfield Road",
      "city" : null,
      "state" : null,
      "postalCode" : "12345",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/2"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/2"
        }
      }
    }, {
      "street" : "Brindley Rd",
      "city" : null,
      "state" : null,
      "postalCode" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/3"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/3"
        }
      }
    }, {
      "street" : "Abbey Rd",
      "city" : "London",
      "state" : null,
      "postalCode" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/4"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/4"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1/addresses"
    }
  }
}

6. To edit an address do a PATCH request to http://localhost:8080/addresses/2

{"street": "Glenfield Rd", "city":"Leicester"}

http://localhost:8080/people/1/addresses now returns:

{
  "_embedded" : {
    "addresses" : [ {
      "street" : "Glenfield Rd",
      "city" : "Leicester",
      "state" : null,
      "postalCode" : "12345",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/2"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/2"
        }
      }
    }, {
      "street" : "Brindley Rd",
      "city" : null,
      "state" : null,
      "postalCode" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/3"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/3"
        }
      }
    }, {
      "street" : "Abbey Rd",
      "city" : "London",
      "state" : null,
      "postalCode" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/4"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/4"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1/addresses"
    }
  }
}

7. To delete the Brindley Rd address do a DELETE request to http://localhost:8080/addresses/3

http://localhost:8080/people/1/addresses now returns:

{
  "_embedded" : {
    "addresses" : [ {
      "street" : "Glenfield Rd",
      "city" : "Leicester",
      "state" : null,
      "postalCode" : "12345",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/2"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/2"
        }
      }
    }, {
      "street" : "Abbey Rd",
      "city" : "London",
      "state" : null,
      "postalCode" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/addresses/4"
        },
        "address" : {
          "href" : "http://localhost:8080/addresses/4"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1/addresses"
    }
  }
}

8. To list the Persons and see their count, just go to http://localhost:8080/people