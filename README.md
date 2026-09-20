DOCUMENTAÇÃO DO PROJETO - SISTEMA DE GERENCIAMENTO DE EVENTOS
1. Descrição do Problema
O gerenciamento manual de eventos de pequeno e médio porte costuma gerar falhas operacionais significativas, como a sobreposição de horários em uma mesma sala, alocação simultânea incorreta de palestrantes, estouro de lotação máxima de participantes e duplicidade em listas de inscrição. O problema central consiste em desenvolver uma solução computacional orientada a objetos capaz de automatizar e controlar com precisão o fluxo de dados e as restrições operacionais desse domínio.

2. Objetivo do sistema
Automatizar o fluxo de gestão de eventos por meio da modelagem computacional de entidades do mundo real, permitindo o controle rigoroso de lotações máximas, prevenção de inscrições duplicadas, organização de cronogramas sem conflitos de espaço ou tempo, e o gerenciamento integrado de participantes, palestrantes e atividades associadas.

3. Descrição das funcionalidades
•	Gestão de Eventos: Permite cadastrar novos eventos informando o nome e a capacidade máxima de participantes, além de listar os eventos armazenados e alternar o evento ativo de contexto.
•	Cadastro de Participantes: Realiza a inscrição de participantes vinculados ao evento selecionado, validando restrições de capacidade e integridade.
•	Cadastro de Atividades: Permite registrar atividades vinculadas a um evento específico, associando local, horário, duração e os dados do palestrante responsável.
•	Busca Recursiva de Participantes: Permite localizar um participante inscrito no evento ativo informando o seu CPF, utilizando um algoritmo de busca recursiva sobre o vetor de armazenamento.
•	Listagem de Atividades: Exibe todas as atividades cadastradas no evento ativo, detalhando local, horário e palestrante correspondente.

4. Descrição das classes
•	Main (Camada View): Classe de interface textual via console (Scanner). Gerencia o fluxo de navegação por menus, captura dados primitivos do usuário, trata exceções de entrada numérica e direciona as operações para a camada de serviço.
•	GerenciadorService (Camada Service): Centraliza as regras de negócio globais do sistema. Controla o vetor estático de eventos (Evento[]), valida a integridade de dados (como formatação de CPF e e-mail) e previne conflitos de horários e alocação de palestrantes.
•	Evento (Camada Model): Representa a entidade central de domínio. Armazena o nome, a capacidade máxima e gerencia os vetores estáticos de participantes (Participante[]) e atividades (Atividade[]), controlando seus respectivos índices de inserção e implementando a busca recursiva de participantes.
•	Atividade (Camada Model): Representa as programações que ocorrem dentro de um evento. Contém atributos como nome, local, horário, duração em minutos, além de manter associações diretas com o Palestrante e o Evento correspondente.
•	Participante (Camada Model): Modela os frequentadores do evento, armazenando nome, CPF e e-mail de forma encapsulada.
•	Palestrante (Camada Model): Modela os profissionais responsáveis pelas atividades, contendo nome, CPF e área de especialidade.

5. Relacionamentos entre as classes
•	GerenciadorService  - Evento: Associação do tipo "Um para Muitos" (1 para N), onde o gerenciador armazena um vetor de até 10 instâncias de Evento.
•	Evento - Participante: Composição/Agregação do tipo "Um para Muitos" (1 para N), controlada por um vetor estático dimensionado conforme a capacidade máxima do evento.
•	Evento - Atividade: Agregação do tipo "Um para Muitos" (1 para N), onde cada evento mantém um vetor de até 10 atividades associadas.
•	Atividade - Palestrante: Associação do tipo "Muitos para Um" (N para 1), permitindo que um palestrante ministre diversas atividades ao longo do sistema.
•	Atividade - Evento: Associação do tipo "Muitos para Um" (N para 1), indicando a qual evento pertence cada atividade cadastrada.

6. Regras de negócio
1.	Unicidade de Inscrição: Um participante não pode realizar duas inscrições no mesmo evento utilizando o mesmo CPF.
2.	Capacidade Máxima: O sistema impede novas inscrições de participantes caso o evento já tenha atingido o seu limite máximo estipulado.
3.	Associação Obrigatória: Toda atividade gerada deve estar estritamente vinculada a um evento válido.
4.	Disponibilidade de Local e Horário: O sistema valida e impede o cadastro de duas atividades diferentes que ocupem simultaneamente o mesmo local e o mesmo horário.
5.	Disponibilidade do Palestrante: O sistema impede que um mesmo palestrante seja alocado em duas atividades distintas no mesmo horário.
6.	Validação de Formato: A integridade dos dados cadastrais é assegurada por validações rígidas de CPF (exatamente 11 dígitos numéricos) e e-mail (presença do caractere @).

7. Dificuldades encontradas durante o desenvolvimento
•	Restrição de Coleções: A proibição do uso de ArrayList exigiu o planejamento do tamanho fixo dos vetores em memória e o controle manual de variáveis de índice (indiceParticipante, indiceAtividade, indiceEvento) para evitar erros de estouro de vetor.
•	Implementação da Recursividade: Garantir que o método recursivo de busca de participantes tratasse corretamente as posições vazias (null) e os limites exatos do vetor preenchido exigiu testes lógicos detalhados na condição de parada (caso base).
•	Consistência de Múltiplos Contextos: Desenvolver a lógica de seleção de um evento ativo (eventoSelecionado) na interface textual para garantir que os cadastros de participantes e atividades fossem direcionados de forma correta e segura para o objeto correspondente.
