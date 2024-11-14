# TrainerHive

Change log (4º commit)

- Se ha modificado el SDK a la version 21.
- Se han separado los paneles PanelTables y PanelLogin de la clase FramePrincipal.
- Se ha modificado el método swapPanel para hacer más sencillo su uso desde otras clases.
- Se ha cambiado el layout del panel LogIn a null.
- Se ha modificado la animación del logo para que sea más evidente pero sutil.
- La lógica de los listeners de las tablas y la configuración de cada tabla y panel se ha separado en componentes más pequeños. Cada método tiene una única responsabilidad y el código es más fácil de leer y mantener.
- Se ha creado la clase UIConstantes, para almacenar valores fijos de la GUI.
- Se ha creado la clase Main con la lógica de arranque de la aplicación.
- Se han añadido los botones en la toolbar para manipular los entrenamientos.
- Los diálogos ahora se alinean con la ventana principal.
- Los datos de conexión a la base de datos se encuentran ahora en un archivo de propiedades
- Añadidas las funcionalidades de añadir workouts y eliminarlos.

Recursos utilizados:

- FontAwesome para los iconos.
- Claude para consultar ciertas dudas o aprender a manejar ciertos componentes.
- He usado Photoshop para la creación de algún icono o del propio logotipo.

Principales problemas:

- Intentar colocar algunos componentes de GUI se hacía realmente tedioso.
- Tuve problemas al querer utilizar una dependencia como el tema visual.
- Me volví loco por crear getters con formato que luego hacía que por ejemplo no funcionases bien los métodos de DataAccess, ahora tienen un nombre diferente.
- Hacer que la interfaz respondiese en todo momento como quería me ha dado bastantes dolores de cabeza, pero he aprendido mucho.
- He tenido que recurrir a la IA para resolver dudas sobre los listeners, el método en DataAccess para eliminar Workouts, dar formato en español a las fechas, el manejo de las propiedades, ciertos comportamientos que desaba incluir a la ComboBox y alguna otra cosa menor.
- Por una cosa muy tonta que ni recuerdo, estuve bastante atascado para mostrar los ejercicios de cada Workout.


Sketches
![sketchs](https://drive.usercontent.google.com/download?id=1SzAoh2XHIb36fGo5zup9Vu1JZcQkF08i)
