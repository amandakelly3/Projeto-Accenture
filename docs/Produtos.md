### User Story - Produtos

**Título:** Como atendente, quero gerenciar produtos de forma eficiente para garantir que o catálogo esteja sempre atualizado e correto.

**Descrição:**
Como atendente, preciso de uma API que me permita criar, visualizar, atualizar e excluir produtos. Isso me ajudará a manter o catálogo de produtos atualizado, garantindo que todas as informações estejam corretas e disponíveis para os clientes. A API deve ser fácil de usar e fornecer respostas claras para que eu possa resolver qualquer problema rapidamente.

**Critérios de Aceitação:**
1. Deve ser possível criar um novo produto com todas as informações necessárias.
2. Deve ser possível buscar um produto específico pelo seu ID.
3. Deve ser possível atualizar as informações de um produto existente.
4. Deve ser possível excluir um produto.

---

### Casos e Cenários de Teste

#### 1. Criar um Novo Produto (POST /api/produtos)
- **Cenário 1:** Criar um produto com informações válidas.
  - **Dados de Entrada:** JSON com todos os campos obrigatórios preenchidos corretamente.
  - **Resultado Esperado:** O produto é criado com sucesso, e a API retorna o status HTTP `201 (Created)` com os detalhes do produto.

- **Cenário 2:** Tentar criar um produto com informações faltando.
  - **Dados de Entrada:** JSON com campos obrigatórios faltando.
  - **Resultado Esperado:** A API retorna o status HTTP `400 (Bad Request)` com uma mensagem de erro indicando os campos faltantes.

---

#### 2. Buscar um Produto por ID (GET /api/produtos/{id})
- **Cenário 1:** Buscar um produto existente.
  - **Dados de Entrada:** ID de um produto existente.
  - **Resultado Esperado:** A API retorna o status HTTP `200 (OK)` com os detalhes do produto.

- **Cenário 2:** Buscar um produto com ID inexistente.
  - **Dados de Entrada:** ID de um produto que não existe.
  - **Resultado Esperado:** A API retorna o status HTTP `404 (Not Found)` com uma mensagem de erro indicando que o produto não foi encontrado.

---

#### 3. Atualizar um Produto (PUT /api/produtos/{id})
- **Cenário 1:** Atualizar um produto existente com informações válidas.
  - **Dados de Entrada:** JSON com campos atualizados corretamente.
  - **Resultado Esperado:** O produto é atualizado com sucesso, e a API retorna o status HTTP `200 (OK)` com os detalhes atualizados do produto.

- **Cenário 2:** Tentar atualizar um produto com ID inexistente.
  - **Dados de Entrada:** ID de um produto que não existe e JSON com campos atualizados.
  - **Resultado Esperado:** A API retorna o status HTTP `404 (Not Found)` com uma mensagem de erro indicando que o produto não foi encontrado.

---

#### 4. Excluir um Produto (DELETE /api/produtos/{id})
- **Cenário 1:** Excluir um produto existente.
  - **Dados de Entrada:** ID de um produto existente.
  - **Resultado Esperado:** O produto é excluído com sucesso, e a API retorna o status HTTP `204 (No Content)`.

- **Cenário 2:** Tentar excluir um produto com ID inexistente.
  - **Dados de Entrada:** ID de um produto que não existe.
  - **Resultado Esperado:** A API retorna o status HTTP `404 (Not Found)` com uma mensagem de erro indicando que o produto não foi encontrado.

---

#### 5. Listar Todos os Produtos (GET /api/produtos)
- **Cenário 1:** Listar todos os produtos quando existem produtos cadastrados.
  - **Dados de Entrada:** Nenhum.
  - **Resultado Esperado:** A API retorna o status HTTP `200 (OK)` com uma lista de todos os produtos.

- **Cenário 2:** Listar todos os produtos quando não há produtos cadastrados.
  - **Dados de Entrada:** Nenhum.
  - **Resultado Esperado:** A API retorna o status HTTP `200 (OK)` com uma lista vazia.