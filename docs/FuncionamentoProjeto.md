# Projeto E-commerce 

#### Tecnologias e conexões utilizadas:
- **Java - Spring Boot**
- **MySQL**: Utilizado para persistência de dados através das entidades JPA.
- **RabbitMQ**: Utilizado para comunicação assíncrona.

## Funcionamento dos Modulos:

### PedidosAPI 
PedidosAPI é uma aplicação Microsserviços Spring Boot que gerencia pedidos, utilizando MySQL para persistência e RabbitMQ para comunicação assíncrona.

#### Packages e Funções

- **Entity**: Contém as entidades JPA que representam as tabelas do banco de dados.
  - `StatusPedido`: Representa o status de um pedido com atributos como `id`, `descricao`, e `status`.
  - `PedidoHistoricoStatus`: Armazena o histórico de status dos pedidos.
  
- **DTOS**: Contém classes de transferência de dados.
  - `AtualizarPedidoDTO`: Utilizado para atualizar pedidos.
  - `RegisterPedidoDto`: Utilizado para registrar novos pedidos.
  - `PedidoRegisteredPayload`: Payload para eventos de pedidos registrados.
  - `PedidoDTO`: Representa um pedido com atributos como `id`, `descricao`, `valor`, `status`, e `dataHora`.
- **Repository**: Contém interfaces que estendem `JpaRepository` para operações CRUD.
  - `PedidoRepository`: Interface para operações CRUD na entidade `Pedido`.
- **Service**: Contém a lógica de negócios.
  - `PedidoService`: Gerencia pedidos, incluindo criação, atualização, exclusão e listagem. Utiliza `RabbitTemplate` para enviar mensagens ao RabbitMQ.
- **Controller**: Contém os controladores REST.
  - `PedidoController`: Controlador REST para gerenciar pedidos 
- **Listener**: Contém componentes que escutam mensagens do RabbitMQ.
  - `PedidoListener`: Escuta mensagens de pedidos e gera notificações 
------------------------------------------------------------------------------
### ProcessamentoAPI 
ProcessamentoAPI é uma aplicação Spring Boot que gerencia o processamento de pedidos, utilizando MySQL para persistência e RabbitMQ para comunicação assíncrona.

#### Packages e Funções

- **Controller**: Contém os controladores REST.
  - `PedidoController`: Controlador REST para gerenciar pedidos, utilizando o serviço `ProcessamentoService`.
- **Service**: Contém a lógica de negócios.
  - `ProcessamentoService`: Gerencia o processamento de pedidos, incluindo validação e atualização de status.
- **Repository**: Contém interfaces que estendem `JpaRepository` para operações CRUD.
  - `PedidoRepository`: Interface para operações CRUD na entidade `Pedido`.
- **Listener**: Contém componentes que escutam mensagens do RabbitMQ.
  - `PedidoListener`: Escuta mensagens de pedidos e processa as informações recebidas.

### NotificacaoAPI
NotificacaoAPI é uma aplicação Spring Boot que gerencia a notificação de pedidos, utilizando RabbitMQ para comunicação assíncrona.

#### Packages e Funções

- **Config**: Contém classes de configuração.
  - Configurações relacionadas ao RabbitMQ, como filas, exchanges e bindings, são definidas aqui para permitir a comunicação assíncrona.
- **Entity**: Contém as entidades JPA que representam as tabelas do banco de dados.
  - `Pedido`: Representa um pedido com atributos como `id`, `descricao`, `valor`, `status`, e `dataHora`.
- **Listener**: Contém componentes que escutam mensagens do RabbitMQ.
  - `PedidoListener`: Escuta mensagens de pedidos na fila configurada e gera notificações. Utiliza o método `enviarNotificacao` para registrar logs de notificações geradas.
