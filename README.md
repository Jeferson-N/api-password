# Password Validation API

Este projeto é uma API para validação de senhas, desenvolvida com **Spring Boot**. Ele implementa diversas regras de validação e fornece respostas claras sobre a validade de uma senha e os erros encontrados.

## 📋 Funcionalidades

- Validação de senhas com base em regras específicas.
- Retorno de mensagens de erro detalhadas para senhas inválidas.
- Testes automatizados (unitários e de integração) para garantir a qualidade do código.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4**
- **JUnit 5** para testes.
- **MockMvc** para simulação de requisições HTTP em testes.
- **Jackson** para serialização e desserialização de JSON.
- **Maven** como gerenciador de dependências.

## 📂 Estrutura do Projeto

```plaintext
src
├── main
│   ├── java
│   │   └── br.com.jeferson.password
│   │       ├── [PasswordApplication.java](http://_vscodecontentref_/0)  # Classe principal da aplicação
│   │       └── v1
│   │           ├── controller            # Controladores REST
│   │           ├── dto                   # Objetos de transferência de dados
│   │           ├── rule                  # Regras de validação de senha
│   │           └── service               # Lógica de negócios
│   └── resources
│       └── application.properties        # Configurações da aplicação
└── test
    ├── java
    │   └── br.com.jeferson.password
    │       ├── PasswordApplicationTests.java  # Testes gerais da aplicação
    │       └── v1
    │           ├── controller
    │           │   ├── integration       # Testes de integração
    │           │   └── unit              # Testes unitários
    │           ├── rule
    │               └── unit              # Testes unitários das regras de validação
    │           └── service               # Testes dos serviços
    │               └── unit              # Testes unitários do serviço
```

## 📜 Regras de Validação de Senhas

As seguintes regras são aplicadas para validar uma senha:

1. Deve ter pelo menos 9 caracteres.
2. Deve conter pelo menos uma letra maiúscula.
3. Deve conter pelo menos uma letra minúscula.
4. Deve conter pelo menos um número.
5. Deve conter pelo menos um caractere especial (!@#$%^&\*()-+).
6. Não deve conter caracteres repetidos.

## 🛠️ Como Executar o Projeto

Clone o repositório:

```
git clone https://github.com/Jeferson-N/api-password

cd password
```

Compile e execute a aplicação:

```
./mvnw spring-boot:run
```

Acesse a API:

- Endpoint principal: POST /api/v1/password/validate

- Exemplo de payload:

```
{
  "password": "Pas$word123"
}
```

Resposta esperada:

- Para uma senha válida:

```
{
  "valid": true
}
```

- Para uma senha inválida:

```
{
  "valid": false,
  "errors": [
    "Password must be at least 9 characters long.",
    "Password must contain at least one digit."
  ]
}
```

✅ Testes
Para executar os testes automatizados:

```
./mvnw test
```

Os testes incluem:

- Testes unitários: Validação de regras e serviços.
- Testes de integração: Validação dos endpoints da API.

📄 Licença
Este projeto está licenciado sob a MIT License.

Desenvolvido por Jeferson.
