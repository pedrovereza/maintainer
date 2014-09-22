##Relatório:

As novas funcionalidades solicitadas foram incluídas na **versão texto do aplicativo**. Foram elas:


* 	Criação da enumeração Status, que indica os possiveis estados de uma Transfer (PENDING, FINISHED, CANCELED)


*	Verificação da quantidade e OperationLocation no método transfer da classe CurrentAccount, adicionando uma Transfer com status específico (PENDING ou FINISHED) na lista.

* 	Criação do método getAllTransfersPending() na classe CurrentAccount que retorna uma lista imutável (desejou-se evitar que outros objetos possam alterar ou remover elementos desta lista) que contém apenas as transferências com status PENDING.


*	Inclusão dos métodos gelAllPendingTransfers(), cancel(Transfer) e authorize(Transfer) na interface AccountManagementService, bem como suas implementações na classe AccountManagementServiceImpl.

*	Criação do método finalizeTransfer(Transfer) em CurrentAccount, que faz o depósito do valor da transferência na conta destino (quando a transferência é autorizada).

*	Criação do método cancelTransfer(Transfer) em CurrentAccount, que faz o depósito do valor da transferência na conta origem (quando a transferência não é autorizada).

 	
*	AuthorizeTransferCommand: Novo Command criado para que o funcionário possa visualizar e autorizar/cancelar as transferências pendentes. Seguiu-se os padrões encontrados em outras implementações de Command (mensagens de textos nos arquivos de resource, por exemplo).

*	Diversos métodos private nas classes mencionadas, visando uma melhor legibilidade e manutenabilidade do código (estes métodos não foram citados aqui pois não fazem parte das interfaces públicas das classes).

Durante o desenvolvimento, aplicou-se a técnica de TDD (Test Driven Development) sempre que possível. Os testes demandaram a inclusão do framework Mockito para criação de Mocks. Todos os testes implementados podem ser vistos no pacote de test.	

Os diagramas a seguir representam todas as alterações necessárias para a inclusão da nova funcionalidade. Diagramas de classe possuem classes inalteradas apenas para entendimento geral da relação entre os objetos. Os diagramas de sequência incluem fluxos que foram modificados ou criados, não apresentando situações que se mantiveram iguais (por exemplo, apenas a transferência com valor acima de 5000 foi exemplificada no diagrama "Transfer ATM", uma vez que os passos não foram alterados para transferências em Agencia ou transferências com valores menores que 5000).
