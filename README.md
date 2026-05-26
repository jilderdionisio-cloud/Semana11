# Petclinic Test - Instrucciones de la actividad

Este README describe la actividad de pruebas unitarias para la aplicación petclinic_test y contiene los comandos necesarios para ejecutar las pruebas y coordinar el trabajo con Git.

Requisitos
- Java JDK 11+ instalado
- Maven instalado (mvn en PATH)
- Repositorio clonado localmente (root del proyecto contiene pom.xml)

Estructura relevante
- Código fuente: src/main/java
- Pruebas: src/test/java
- Reportes de pruebas: target/surefire-reports (text/xml), `mvn surefire-report:report` genera HTML en target/site

Objetivo de la actividad
Realizar pruebas unitarias (creación, actualización, búsqueda y eliminación) para las tablas y servicios indicados, trabajando en coordinación con el equipo usando Git. Guardar evidencia de la ejecución y del control de versiones.

Casos de prueba

CASO 1 — Vets
- Clase: VetServiceTest
- Objetivo: pruebas unitarias de creación (create), actualización (update), búsqueda (findById/findAll) y eliminación (delete) de veterinarios.
- Ubicación: src/test/java/.../VetServiceTest
- Evidencia: salida de consola de mvn, archivos en target/surefire-reports, capturas de pantalla, commits y PRs con los cambios.

CASO 2 — Owners
- Clase: OwnerServiceTest
- Objetivo: pruebas unitarias de creación, actualización, búsqueda y eliminación de dueños.
- Ubicación: src/test/java/.../OwnerServiceTest
- Evidencia: idem CASO 1.

CASO 3 — Specialties
- Clase: SpecialtyServiceTest
- Objetivo: pruebas unitarias de creación, actualización, búsqueda y eliminación de especialidades.
- Ubicación: src/test/java/.../SpecialtyServiceTest
- Evidencia: idem CASO 1.

CASO 4 — Visits
- Clase: VisitServiceTest
- Objetivo: pruebas unitarias de creación, actualización, búsqueda y eliminación de visitas.
- Ubicación: src/test/java/.../VisitServiceTest
- Evidencia: idem CASO 1.

Comandos Git (workflow recomendado por caso)
1. Actualizar rama principal:
   git fetch origin --prune
   git checkout main
   git pull origin main --ff-only

2. Crear rama de trabajo (por ejemplo para vets):
   git checkout -b feature/tests-vets

3. Hacer cambios, ejecutar pruebas localmente, agregar y commitear:
   git add -A
   git commit -m "Add unit tests for VetServiceTest"
   git push -u origin feature/tests-vets

4. Abrir Pull Request y pedir revisión de equipo. Incluir en la PR las evidencias (stdout, screenshots, target/surefire-reports).

Comandos Maven para ejecutar pruebas
- Ejecutar todas las pruebas del proyecto:
  mvn test

- Ejecutar una sola clase de prueba (por nombre de clase):
  mvn -Dtest=VetServiceTest test
  mvn -Dtest=OwnerServiceTest test
  mvn -Dtest=SpecialtyServiceTest test
  mvn -Dtest=VisitServiceTest test

- Ejecutar una clase con paquete completo (si hay conflictos de nombre):
  mvn -Dtest=com.tecsup.petclinic.services.VetServiceTest test

- Generar reporte HTML de Surefire (opcional):
  mvn surefire-report:report
  abrir target/site/surefire-report.html

Evidencia requerida
- Registro de la ejecución (guardar la salida del terminal o redirigir a archivo):
  mvn -Dtest=VetServiceTest test | tee vet-tests.log
- Archivos XML/TXT en target/surefire-reports/ (adjuntar en la entrega)
- Capturas de pantalla de la ejecución y del reporte HTML
- Historial de Git: URL del PR, commits con mensajes claros y branch usado

Buenas prácticas de coordinación
- Hacer una rama por caso o por conjunto de tests relacionados
- Commits pequeños y atómicos con mensajes descriptivos
- Abrir PR y asignar revisores del equipo
- Incluir en la PR la evidencia mencionada

Soporte y contacto
- Para problemas en la ejecución de pruebas, revisar dependencias en pom.xml y logs en target/surefire-reports
- Si los tests fallan por datos, usar mocks o datos de prueba aislados y documentar el motivo en el PR

---
Archivo actualizado por el asistente. Si desea, puedo commitear y pushear este README a la rama actual o a una nueva rama de trabajo.