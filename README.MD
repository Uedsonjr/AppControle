# App de Controle

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?style=for-the-badge&logo=openapi-initiative&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-003545?style=for-the-badge&logo=h2&logoColor=white)


## Descrição do Projeto

O **App de Controle** é uma aplicação em Java desenvolvida com Spring Boot e JPA, seguindo o padrão arquitetural MVC (Model-View-Controller). 
A aplicação possibilita adicionar, atualizar, excluir e consultar Pessoas e seus respectivos Contatos.

## Estrutura do Projeto

- **Model**: Representa os modelos de dados armazenados no banco de dados.
- **Controller**: Disponibiliza APIs REST para interação com as entidades criadas.
- **Service**: Abriga a lógica de negócio da aplicação e seus funcionais.


### Direcionamentos para os diretórios principais:

- [Model](src/main/java/br/com/uedsonjr/AppControle/model)
- [Controller](src/main/java/br/com/uedsonjr/AppControle/resource)
- [Service](src/main/java/br/com/uedsonjr/AppControle/service)

## Requisitos

- Java 17+ e Maven 3+.

## Configuração do Banco de Dados

A aplicação utiliza o banco de dados em memória H2, que está configurado para rodar na porta 8080 localmente.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-nome/AppControle.git
   cd AppControle
   ```

2. Compile e execute a aplicação utilizando Maven:
   ```bash
   mvn spring-boot:run
   ```

3. Acesse o banco de dados H2:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User Name: `sa`
   - Password: `admin`

## Documentação da API

A documentação da API está disponível no formato OpenAPI (Swagger). Para acessar a documentação, utilize o seguinte link:
- [Documentação OpenAPI Swagger](http://localhost:8080/swagger-ui.html)

## Exemplos de Requisições

### Adicionar uma Pessoa

**Requisição:**
```bash
POST /api/pessoas
```

**Body:**
```json
{
  "nome": "Rusbinho",
  "endereco": "Avenida bolo, 111",
  "cep": "12345-010",
  "cidade": "Minas Gerais",
  "uf": "MG"
}
```

### Buscar uma Pessoa por ID

**Requisição:**
```bash
GET /api/pessoas/{id}
```

### Atualizar uma Pessoa

**Requisição:**
```bash
PUT /api/pessoas/{id}
```

### Excluir uma Pessoa

**Requisição:**
```bash
DELETE /api/pessoas/{id}
```

### Buscar Contatos por Pessoa

**Requisição:**
```bash
GET /api/contatos/pessoa/{id}
```

---