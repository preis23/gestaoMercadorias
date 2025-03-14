# gestaoMercadorias
Engenharia Redes e Sistemas de Computadores
Programação 2 Trabalho Prático
Ano letivo de 2024/2025 Gestão de Mercadorias em Armazéns
Este trabalho tem como principal objetivo implementar na linguagem Java uma aplicação de simulação armazenamento de mercadorias em armazéns.

Descrição

Desenvolver um simulador de rede de dispositivos IoT (Internet das Coisas) para gestão de armazéns e monitorização das mercadorias neles armazenadas. Isso pode incluir sensores, atuadores, e dispositivos inteligentes que se comunicam entre si.
Cada mercadoria é identificada por uma tag IoT que permite identificar a sua localização. Sempre que uma mercadoria é mudada de local dentro do armazém ou sai do armazém para um meio de transporte externo, o sistema terá capacidade de detetar e atualizar a localização da mercadoria.
As mercadorias tem associadas um identificador único, uma descrição, um tipo, um peso, um volume e a tag IoT. As mercadorias podem ser normais, frágeis, perecíveis, devendo ser tratadas de forma diferente. Por exemplo, uma mercadoria frágil não deve ser movimentada internamente num armazém juntamente com outras mercadorias e deve ser movimentada para fora do armazém em meio de transporte especial. Por outro lado, uma mercadoria perecível deve ter em consideração eventuais datas de validade para que mercadorias perecíveis do mesmo tipo sejam escoadas do armazém por ordem dessa data, evitando assim que as mesmas fiquem estragadas. Nesse caso até devem ser movimentadas para um armazém de reciclagem por transporte especial.
Os armazéns têm um nome, uma morada, as dimensões (espaço útil de armazenamento). Qualquer armazém pode receber ou expedir mercadorias, exceto os armazéns de reciclagem que apenas recebem mercadorias.
Os meios de transporte de mercadorias podem ser internos a um armazém (ex. empilhadores) ou externos (ex. camiões). Esse meio de transporte tem um identificador, bem como uma capacidade de transporte limitada por peso e volume. O transporte de uma mercadoria para um armazém de reciclagem apenas pode ser efetuado por um transporte especial.
A aplicação deverá permitir criar armazéns; meios de transporte (internos ou externos); mercadorias e registar as mercadorias nos armazéns. Deve igualmente criar tags IoT e associá- las às mercadorias.
Sempre que uma mercadoria é registada num armazém será necessário controlar se este tem espaço suficiente para a armazenar. Sempre que é movimentada uma mercadoria por um meio de transporte, seja interno ou externo ao armazém, será necessário controlar se o meio de transporte tem capacidade para transportar e se está habilitado para esse transporte.
Relativamente às mercadorias, a sua movimentação é controlada pelas tags IoT sendo possível consultar as mercadorias por tag, localização e estado. A saída e entrada de mercadorias, seja nos armazéns, seja nos meios de transporte, deve ser detetada automaticamente, igualmente pelas tags IoT.
A aplicação deve garantir a persistência dos dados relevantes à mesma em ficheiro. Deve igualmente produzir relatórios em ficheiro de texto que permitam listar as mercadorias atuais de um determinado armazém ou meio de transporte. Deve ainda produzir relatórios de rastreabilidade de uma mercadoria, podendo assim saber-se os armazéns e os camiões por onde determinada mercadoria passou.
