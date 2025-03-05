db = connect("mongodb://localhost:27017/admin");

db = db.getSiblingDB("app_database");

db.createCollection("clients", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["name", "email", "cpf", "phoneNumber"],
      properties: {
        name: {
          bsonType: "string",
          description: "Nome do cliente (obrigatório)"
        },
        email: {
          bsonType: "string",
          pattern: "^.+@.+\\..+$",
          description: "E-mail do cliente (obrigatório, deve ser válido)"
        },
        cpf: {
          bsonType: "string",
          pattern: "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$",
          description: "CPF do cliente (obrigatório, formato XXX.XXX.XXX-XX)"
        },
        phoneNumber: {
          bsonType: "string",
          description: "Número de telefone do cliente (obrigatório)"
        }
      }
    }
  }
});

db.createCollection("medications", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["name", "brand", "price", "dosage"],
      properties: {
        name: {
          bsonType: "string",
          description: "Nome do medicamento (obrigatório)"
        },
        brand: {
          bsonType: "string",
          description: "Marca do medicamento (obrigatório)"
        },
        price: {
          bsonType: "decimal",
          description: "Preço do medicamento (obrigatório)"
        },
        dosage: {
          bsonType: "decimal",
          description: "Dosagem do medicamento (obrigatório)"
        }
      }
    }
  }
});

db.createCollection("clients_medications", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["clientId", "medicationId", "monthlyRenewalDay"],
      properties: {
        id: {
          bsonType: "string",
          description: "must be a string and is the unique identifier for the client medication"
        },
        clientId: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        medicationId: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        monthlyRenewalDay: {
          bsonType: "int",
          description: "must be an integer and is required"
        },
        client: {
          bsonType: "object",
          description: "must be an object representing the client entity"
        }
      }
    }
  }
});

db.createCollection("subscriptions", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: [
        "clientId",
        "clientName",
        "clientEmail",
        "clientPhoneNumber",
        "medicationId",
        "medicationName",
        "medicationBrand",
        "medicationDosage",
        "medicationMonthlyRenewalDay"
      ],
      properties: {
        id: {
          bsonType: "string",
          description: "must be a string and is the unique identifier for the subscription"
        },
        clientId: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        clientName: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        clientEmail: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        clientPhoneNumber: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        medicationId: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        medicationName: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        medicationBrand: {
          bsonType: "string",
          description: "must be a string and is required"
        },
        medicationDosage: {
          bsonType: "decimal",
          description: "must be a decimal and is required"
        },
        medicationMonthlyRenewalDay: {
          bsonType: "int",
          description: "must be an integer and is required"
        }
      }
    }
  }
});

print("Coleção 'clients' criada com sucesso!");
print("Coleção 'medications' criada com sucesso!");
print("Coleção 'clients_medications' criada com sucesso!");
print("Coleção 'subscriptions' criada com sucesso!");
