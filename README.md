# Petclinic Test — Guía de la actividad

Guía para ejecutar las pruebas unitarias de la aplicación petclinic_test y coordinar el trabajo con el equipo usando Git.

Requisitos
- Java JDK 11+ instalado
- Maven instalado (mvn en PATH)
- Repositorio clonado localmente (contiene pom.xml)

Estructura relevante
- Código: src/main/java
- Pruebas: src/test/java
- Reportes: target/surefire-reports (XML/TXT), HTML en target/site tras surefire-report

Resumen de la actividad
Realizar pruebas unitarias (crear, actualizar, buscar y eliminar) para los servicios correspondientes a las tablas: vets, owners, specialties y visits. Guardar evidencia de ejecución y del control de versiones.

Casos de prueba
- CASO 1 — Vets: Clase VetServiceTest (crear, actualizar, buscar, eliminar)
- CASO 2 — Owners: Clase OwnerServiceTest (crear, actualizar, buscar, eliminar)
- CASO 3 — Specialties: Clase SpecialtyServiceTest (crear, actualizar, buscar, eliminar)
- CASO 4 — Visits: Clase VisitServiceTest (crear, actualizar, buscar, eliminar)

Evidencia esperada
- Salida de terminal de mvn (guardar en archivo)
- Archivos en target/surefire-reports/
- Capturas de pantalla y/o reporte HTML
- Commits y Pull Requests con mensajes claros y branch por caso

Comandos rápidos (copiar/pegar)

- Actualizar rama principal:

```bash
git fetch origin --prune
git checkout main
git pull origin main --ff-only
```

- Crear rama de trabajo (ejemplo vets):

```bash
git checkout -b feature/tests-vets
```

- Ejecutar todas las pruebas:

```bash
mvn test -Dspring.profiles.active=h2
```

- Ejecutar una sola clase de prueba (por nombre):

```bash
mvn -Dtest=VetServiceTest test
mvn -Dtest=OwnerServiceTest test
mvn -Dtest=SpecialtyServiceTest test
mvn -Dtest=VisitServiceTest test
```

- Ejecutar por paquete completo (si hay ambigüedad):

```bash
mvn -Dtest=com.tecsup.petclinic.services.VetServiceTest test
```

- Redirigir salida a archivo (ejemplo Vet):

```bash
mvn -Dtest=VetServiceTest test | tee vet-tests.log
```

- Generar reporte HTML de Surefire:

```bash
mvn surefire-report:report
# abrir target/site/surefire-report.html
```

Flujo Git recomendado (copy/paste)

```bash
# 1. Actualizar main
git fetch origin --prune
git checkout main
git pull origin main --ff-only

# 2. Crear rama específica
git checkout -b feature/tests-<caso>

# 3. Hacer cambios, agregar y commitear
git add -A
git commit -m "Add unit tests for <Caso>"

# 4. Subir y abrir PR
git push -u origin feature/tests-<caso>
# Crear PR en GitHub y asignar revisores
```

Buenas prácticas
- Una rama por caso
- Commits pequeños y descriptivos
- Incluir evidencia en la PR (logs, capturas, reportes)
- Si una prueba falla por datos, usar mocks o datos de prueba aislados y documentar el motivo

Soporte
Revisar pom.xml y target/surefire-reports en caso de fallos. Preguntar al equipo si hay dudas sobre datos de prueba o configuración.

---
README mejorado y formateado. Subiendo al remoto origin/main con commit y Co-authored-by.