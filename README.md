# Payments - Plugin de Pagamento para Minecraft (PIX Integration)

Payments é um plugin para servidores Minecraft que permite a monetização dentro do jogo, integrando pagamentos via PIX. Os jogadores podem comprar itens, permissões, kits e outros recursos usando pagamentos reais, com integração ao sistema de pagamentos via PIX, e recebendo recompensas diretamente no jogo.

## Características Principais

- **Pagamentos via PIX**: Geração de QR Codes para pagamentos com PIX diretamente no Minecraft.
- **Sistema de Loja**: Permite aos administradores criar lojas personalizadas dentro do jogo, com itens, permissões e kits.
- **Histórico de Transações**: Acompanhamento completo das compras dos jogadores.
- **Sistema de Recompensas**: Receba recompensas automáticas ao completar pagamentos, como itens, permissões e comandos.
- **Integração com Discord**: Notifique os administradores ou jogadores sobre compras feitas através do Discord.
- **Sistema de Permissões**: Controle quem pode acessar a loja ou comprar itens usando permissões personalizadas.

## Instalação

### Passo 1: Baixar o Plugin

1. Baixe o arquivo JAR do plugin do [repositório GitHub](https://github.com/SEU-REPOSITORIO) ou compile o código você mesmo.

### Passo 2: Adicionar ao Servidor

1. Coloque o arquivo `.jar` na pasta `plugins` do seu servidor Minecraft.

### Passo 3: Reiniciar o Servidor

1. Após colocar o arquivo `.jar` na pasta `plugins`, reinicie o servidor para carregar o plugin.

### Passo 4: Configuração Inicial

- O arquivo de configuração padrão (`config.yml`) será gerado automaticamente. Para configurá-lo, basta editar o arquivo dentro da pasta `plugins/Payments`.
  
---

## Como Usar

### 1. **Comandos de Administração**

- `/payments shop` - Abre o menu de compra de produtos.
- `/payments history` - Mostra o histórico de compras do jogador.
- `/payments buy <produto>` - Comando para comprar um produto direto pela linha de comando.

### 2. **Menus Interativos**

O plugin oferece menus interativos com categorias e produtos. Cada produto pode ser configurado para ser um **item**, uma **permissão** ou um **comando** no jogo. Para abrir o menu de categorias:

1. Digite `/payments shop` para abrir o menu de categorias.
2. Navegue pelas categorias para selecionar os itens ou serviços que deseja comprar.

### 3. **Realizando Compras**

Ao selecionar um produto no menu, o jogador será redirecionado para a geração de um QR Code PIX, onde ele poderá pagar diretamente através do aplicativo bancário. Após o pagamento confirmado, o jogador receberá sua recompensa automaticamente.

### 4. **Histórico de Transações**

Cada jogador pode visualizar seu histórico de transações com o comando `/payments history`, onde ele verá detalhes de todas as compras realizadas no servidor.

---

## Configuração e Personalização

### 1. **Configuração do Produto**
No arquivo `config.yml`, você pode configurar as categorias e produtos disponíveis para compra. Cada produto tem os seguintes atributos:

- `name`: Nome do produto.
- `description`: Descrição do produto.
- `price`: Preço do produto.
- `category`: Categoria do produto (Ex: Cosméticos, Kits, Permissões).
- `resourceType`: Tipo de recurso (Ex: "item", "permission", "command").
- `action`: Ação a ser realizada com o produto (comando para ser executado, permissão a ser dada, etc.).

Exemplo de configuração no `config.yml`:

```yaml
products:
  - name: "Espada Diamante"
    description: "Espada poderosa para combates épicos"
    price: 50.0
    category: "Cosméticos"
    resourceType: "item"
    action: "diamond_sword"
  - name: "VIP Permissão"
    description: "Acesso VIP para seu servidor"
    price: 100.0
    category: "Permissões"
    resourceType: "permission"
    action: "payments.vip"
  - name: "Comando de Kit"
    description: "Ganhe um kit exclusivo"
    price: 30.0
    category: "Kits"
    resourceType: "command"
    action: "/kit vip"
