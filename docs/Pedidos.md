### User Story  - Pedidos

**Título:** Como atendente, quero gerenciar *pedidos* de forma eficiente para garantir que os clientes recebam seus produtos corretamente.

**Descrição:**
Como atendente, preciso de uma API que me permita criar, visualizar, atualizar e excluir pedidos. Isso me ajudará a manter o controle dos pedidos dos clientes, garantindo que todas as informações estejam atualizadas e corretas. A API deve ser fácil de usar e fornecer respostas claras para que eu possa resolver qualquer problema rapidamente.

**Critérios de Aceitação:**
1. Deve ser possível criar um novo pedido com todas as informações necessárias.
2. Deve ser possível buscar um pedido específico pelo seu ID.
3. Deve ser possível atualizar as informações de um pedido existente.
4. Deve ser possível excluir um pedido.

---

### Casos e Cenários de Teste - Pedidos

#### 1. Criar um Novo Pedido (POST /api/pedidos)
- **Cenário 1:** Criar um pedido com informações válidas.
  - **Dados de Entrada:** JSON com todos os campos obrigatórios preenchidos corretamente.
  - **Resultado Esperado:** O pedido é criado com sucesso, e a API retorna o status HTTP 201 (Created) com os detalhes do pedido.

- **Cenário 2:** Tentar criar um pedido com informações faltando.
  - **Dados de Entrada:** JSON com campos obrigatórios faltando.
  - **Resultado Esperado:** A API retorna o status HTTP 400 (Bad Request) com uma mensagem de erro indicando os campos faltantes.

---

#### 2. Buscar um Pedido por ID (GET /api/pedidos/{id})
- **Cenário 1:** Buscar um pedido existente.
  - **Dados de Entrada:** ID de um pedido existente.
  - **Resultado Esperado:** A API retorna o status HTTP 200 (OK) com os detalhes do pedido.

- **Cenário 2:** Buscar um pedido com ID inexistente.
  - **Dados de Entrada:** ID de um pedido que não existe.
  - **Resultado Esperado:** A API retorna o status HTTP 404 (Not Found) com uma mensagem de erro indicando que o pedido não foi encontrado.

---

#### 3. Atualizar um Pedido (PUT /api/pedidos/{id})
- **Cenário 1:** Atualizar um pedido existente com informações válidas.
  - **Dados de Entrada:** JSON com campos atualizados corretamente.
  - **Resultado Esperado:** O pedido é atualizado com sucesso, e a API retorna o status HTTP 200 (OK) com os detalhes atualizados do pedido.

- **Cenário 2:** Tentar atualizar um pedido com ID inexistente.
  - **Dados de Entrada:** ID de um pedido que não existe e JSON com campos atualizados.
  - **Resultado Esperado:** A API retorna o status HTTP 404 (Not Found) com uma mensagem de erro indicando que o pedido não foi encontrado.

---

#### 4. Excluir um Pedido (DELETE /api/pedidos/{id})
- **Cenário 1:** Excluir um pedido existente.
  - **Dados de Entrada:** ID de um pedido existente.
  - **Resultado Esperado:** O pedido é excluído com sucesso

- **Cenário 2:** Tentar excluir um pedido com ID inexistente.
  - **Dados de Entrada:** ID de um pedido que não existe.
  - **Resultado Esperado:** A API retorna o status HTTP 404 (Not Found) com uma mensagem de erro indicando que o pedido não foi encontrado.

---

#### 5. Listar Todos os Pedidos (GET /api/pedidos)
- **Cenário 1:** Listar todos os pedidos quando existem pedidos cadastrados.
  - **Dados de Entrada:** Nenhum
  - **Resultado Esperado:** A API retorna o status HTTP 200 (OK) com uma lista de todos os pedidos.

- **Cenário 2:** Listar todos os pedidos quando não há pedidos cadastrados.
  - **Dados de Entrada:** Nenhum
  - **Resultado Esperado:** A API retorna o status HTTP 200 (OK) com uma lista vazia.