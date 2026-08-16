# Editor de Figuras 2D

Este é um projeto de editor gráfico interativo que permite a criação, manipulação e remoção de figuras geométricas em um canvas.

## Figuras Suportadas
O sistema permite a criação e manipulação das seguintes figuras base:
1. **Retângulo**
2. **Círculo / Elipse**
3. **Triângulo**
4. **Linha**

---

## Manual de Instruções e Comandos

A interação com o programa é feita através de uma combinação fluida de mouse e teclado.

### 🖱️ Interações com o Mouse
O mouse é o principal controlador espacial da aplicação. As operações seguem o padrão apontar-e-clicar:

* **Foco e Z-Order (Clicar)**: 
  * Ao clicar sobre qualquer figura, ela ganha o **foco**. 
  * O Z-order é atualizado instantaneamente: a figura clicada é trazida para a frente de todas as outras (sobreposição).
* **Posição (Clicar e Arrastar)**: 
  * Com o botão esquerdo pressionado no centro/corpo da figura em foco, mova o mouse para arrastá-la livremente pela tela.
* **Tamanho (Arrastar pelas bordas)**: 
  * Ao clicar e arrastar os delimitadores (pontos nas extremidades) da figura em foco, seu tamanho (largura e altura) será redimensionado de acordo com o movimento do cursor.

### Comandos de Teclado
Os comandos a seguir controlam a criação, deleção e a alteração visual da figura que está atualmente **em foco**.

#### Criação e Remoção
* `R` - Criar um novo **Retângulo**
* `C` - Criar um novo **Círculo**
* `T` - Criar um novo **Triângulo**
* `L` - Criar uma nova **Linha**
* `Delete` ou `Backspace` - **Remover** a figura atualmente em foco da tela.

#### Propriedades Ajustáveis
* `F` - Abrir seletor/trocar a **Cor de Fundo** (Preenchimento) da figura em foco.
* `B` - Abrir seletor/trocar a **Cor de Contorno** (Borda) da figura em foco.
* `+` / `-` - Aumentar ou diminuir a espessura da linha de contorno.

---

## Como Funciona

A arquitetura do sistema foi desenhada em torno de três pilares fundamentais para gerenciar o estado da tela:

1. **Lista de Figuras Ativas**
   Todas as figuras desenhadas são armazenadas em uma estrutura de dados sequencial (como uma `List` ou `ArrayList`). O Z-order é ditado naturalmente por esta lista: o renderizador desenha as figuras na ordem em que aparecem. Quando uma figura é clicada, ela é movida para o final da lista, garantindo que seja desenhada por último e apareça "por cima".

2. **Figura em Foco**
   O programa mantém um ponteiro de estado (ex: `figuraFocada = null`). Quando ocorre o clique do mouse (interceptado pelo listener), o sistema calcula a intersecção matemática do clique com a área das figuras. A figura interceptada assume a variável de foco. Apenas a `figuraFocada` reage aos atalhos de teclado (mudança de cor, deleção) e exibe os manipuladores visuais para alteração de tamanho.

3. **Listeners de Mouse e Teclado**
   O coração da interatividade. 
   * **MouseListeners / MouseMotionListeners**: Capturam o evento de `mousePressed` para checar colisões e definir o foco, e `mouseDragged` para aplicar o delta (diferença de posição X e Y) na coordenada ou no tamanho da figura focada, repintando o canvas a cada frame.
   * **KeyListeners**: Aguardam as teclas de atalho e roteiam a ação (instanciar um novo objeto na lista ou modificar as propriedades da figura atual).
