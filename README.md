-RESUMEN
Investigación para tesis Agosto-Diciembre 2014 para la carrera Ingeniero en Tecnología de Software de la Facultad de Ingeniería Mecánica y Eléctrica de la Universidad Autónoma de Nuevo León

Este software estará enfocado para motivar a los niños a tener actividad física.
La aplicacion esta siendo desarrollada en Android utilizando la herramienta de desarrollo Android Studio (Beta)

Estructura del sistema esta con el patron de diseño observer, para poder llamar al notificador cada vez que es ejecutado un sensor.
Hasta el momento se lleva implementado un servicio que cuenta pasos con el sensor del acelerometro con ese dato se calcula los demas factores que seran de ayuda para el experimento, estos son cuenta calorias, distancia, velocidad.

Estare utilizando Unity para realizar la parte de el AR, primero hare unos disenos de prueba. 
Encontre un exporter blender -> Vuforia, lo probare para ver que tal.[Actualización] Blender ya no se utilizará.

Se utilizara Log4j para hacer logs de los datos obtenidos

Se utilizara Groovy para implementar el servidor REST por medio de spring
Se esta utilizando Maven para administrar el proyecto del servidor, para compilar y para copiar todas las dependencias que necesitara la parte del servidor
Se contempla la utilización de MongoDB como base de datos ya que puede recibir información en formato JSON 

-CHANGELOG
	SE SIGUE EN LA MODELACION DE LAS CRIATURAS
    SE AGREGO MONGODB COMO BASE DE DATOS
    SE AGREGO LA ENTIDAD DE LA BASE DE DATOS 
    SE UTILIZA SHAREDPREFERENCES PARA GUARDAR LOCALMENTE LOS DATOS Y SE PREPARAN PARA ESCRIBIR EL ARCHIVO QUE SE MANDARA A GUARDAR EN LA BD
    ACTUALIZACION EN LA ESTRUCTURA DEL RENDERER Y EL BUILDER DE LAS CREATURAS A MOSTRAR

