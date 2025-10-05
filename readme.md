## ¿Qué es un árbol Binario?

Un **árbol binario** es una estructura de datos **no lineal** compuesta por un conjunto de **nodos** conectados mediante **ramas**.  
Dentro del árbol existe un nodo principal llamado **raíz**, a partir del cual se originan los demás nodos.

Los nodos que generan nuevas ramas se conocen como **nodos internos** o **nodos de bifurcación**, mientras que los nodos que no tienen descendientes se denominan **nodos hoja**.

En la siguiente imagen se puede observar la estructura general de un árbol binario:

![Estructura de un árbol binario](./image/tree.gif)

## ¿Cómo se implementó?

El proyecto fue desarrollado en **Java** utilizando la biblioteca **Swing** para la creación de la interfaz gráfica.  
Su propósito es **visualizar y gestionar un árbol binario de búsqueda (Binary Search Tree)** de forma interactiva.

### Estructura general
- Se definió una **clase `Node`**, que representa cada nodo del árbol, almacenando un valor entero y referencias a sus nodos izquierdo y derecho.
- La **clase `BinaryTree`** contiene la lógica del árbol binario, incluyendo:
    - El método `insert(int value)` para agregar nuevos valores de forma recursiva.
    - El método `search(int value)` para verificar si un valor existe en el árbol.

### Interfaz gráfica (Swing)
- La ventana principal extiende de `JFrame` y contiene:
    - Un **panel superior (`JPanel`)** con un campo de texto y botones para insertar o buscar números.
    - Un **panel central (`TreePanel`)** que se encarga de **dibujar gráficamente el árbol**.

### Visualización del árbol
- La clase `TreePanel` hereda de `JPanel` y sobrescribe el método `paintComponent(Graphics g)` para dibujar el árbol recursivamente:
    - Cada nodo se representa como un círculo azul con el valor centrado.
    - Las ramas se dibujan como líneas entre nodos.
    - Se utiliza un espaciado horizontal progresivo para mantener la estructura visual clara.

### Interacción del usuario
- Al presionar el botón **“Insertar”**, el valor ingresado se añade al árbol y se redibuja automáticamente.
- Al presionar **“Buscar”**, se muestra un mensaje indicando si el valor se encuentra o no en el árbol.

### Tecnologías utilizadas
- **Lenguaje:** Java
- **Bibliotecas:** Swing y AWT
- **Características:** interfaz visual, dibujo recursivo y manejo de eventos.

## Ejemplo de ejecución

A continuación se muestra un ejemplo visual del funcionamiento del programa paso a paso:

### 1. Inserción de un número
Se ingresa un número en el campo de texto y se presiona el botón **“Insertar”**.

![Inserción de número](./image/01.jpg)

---

### 2. Número agregado al árbol
El número ingresado aparece representado como un **nodo en el árbol binario**.

![Número agregado al árbol](./image/02.jpg)

---

### 3. Inserción de más números
Se agregan más valores y el árbol se **actualiza visualmente** mostrando la estructura generada.

![Más números agregados](./image/03.jpg)

---

### 4. Búsqueda de un número existente
Se ingresa un número y se presiona el botón **“Buscar”** para verificar si está en el árbol.

![Inicio de búsqueda](./image/04.jpg)

---

### 5. Resultado de búsqueda exitosa
El sistema muestra un mensaje indicando que el número **sí se encuentra** en el árbol.

![Número encontrado](./image/05.jpg)

---

### 6. Búsqueda de un número inexistente
Al buscar un número que **no está presente**, se muestra un mensaje indicando que **no fue encontrado**.

![Número no encontrado](./image/06.jpg)


## Contribuyentes

Este trabajo fue desarrollado por el grupo:

- Jhon Eduard Campo – [@Jhon](https://github.com/Jhoncampo)
- Luisa Fernanda Gaviria – [@Luisa](https://github.com/luisagaviriazapata-ai)
- Anderson David Mejia – [@Ander](https://github.com/ander8824425-boop)
