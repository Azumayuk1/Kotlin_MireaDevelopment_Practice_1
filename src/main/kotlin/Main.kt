import java.io.File
import java.io.FileOutputStream
import java.nio.file.FileSystems
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

class PartFiles {
    private lateinit var file: File

    fun workWithFiles() {
        var userInitChoice: Int
        while (true) {
            println(
                "Выберите действие:\n" +
                        "1. Создать файл / Открыть файл\n" +
                        "2. Записать в файл\n" +
                        "3. Прочитать файл\n" +
                        "4. Удалить файл\n" +
                        "0. Выход\n" +
                        "Введите номер:\n"
            )

            userInitChoice = try {
                readln().toInt()
            } catch (e: Exception) {
                -1
            }

            when (userInitChoice) {
                -1 -> println("Неправильный ввод.")
                0 -> break
                1 -> createOrOpenFile()
                2 -> writeToFile()
                3 -> readFromFile()
                4 -> deleteFile()
            }
        }
    }

    fun showDiskInfo() {
        // Вывод информации о логических дисках
        val fileRoots = File.listRoots()
        for (fileRoot in fileRoots) {
            println("Метка тома\t\t:::: ${fileRoot.path[0]}")
            println("Размер\t\t\t:::: ${fileRoot.totalSpace / (1024 * 1024 * 1024)} ГБ")
            println("Свободное место\t:::: ${fileRoot.freeSpace / (1024 * 1024)} МБ")
            println()
        }

        println("Типы файловых систем:")
        // Get the file store associated with the file root
        val fileSystem = FileSystems.getDefault()
        fileSystem.fileStores.forEach { store -> println("Диск: $store \t:::: ${store.type()}") }
        println()
    }

    // РАБОТА С ФАЙЛАМИ

    private fun createOrOpenFile() {
        // Создание / Открытие файла
        println("Введите имя файла (без расширения): ")
        val fileName = readln() + ".txt"
        file = File(fileName)
        file.createNewFile()
        println("Файл создан / открыт: ${file.absolutePath}")
    }

    private fun writeToFile() {
        // Запись в файл
        println("Введите строку для записи в файл:")
        val inputString = readLine()
        file.writeText(inputString ?: "")
    }

    private fun readFromFile() {
        // Чтение файла
        val fileContent = file.readText()
        println("Содержимое файла:")
        println(fileContent)
    }

    private fun deleteFile() {
        // Удаление файла
        println("Файл удаляется: ${file.absolutePath}")
        file.delete()
        println("Файл удален.")
    }

}

// Работа с форматом JSON

data class Person(val name: String, val age: Int, val city: String)

class PartJson {
    private lateinit var jsonFile: File

    fun workWithJson() {
        var userInitChoice: Int
        while (true) {
            println(
                "Выберите действие:\n" +
                        "1. Создать файл JSON / Открыть файл JSON\n" +
                        "2. Записать в файл\n" +
                        "3. Прочитать файл\n" +
                        "4. Удалить файл\n" +
                        "0. Выход\n" +
                        "Введите номер:\n"
            )

            userInitChoice = try {
                readln().toInt()
            } catch (e: Exception) {
                -1
            }

            when (userInitChoice) {
                -1 -> println("Неправильный ввод.")
                0 -> break
                1 -> createOrOpenJson()
                2 -> writeJson()
                3 -> readJson()
                4 -> deleteJson()
            }
        }
    }

    companion object {
        fun createPerson(): Person {
            println("Введите имя: ")
            val name = readln()
            println("Введите возраст: ")
            val age = try {
                readln().toInt()
            } catch (e: Exception) {
                -1
            }
            println("Введите город:")
            val cityName = readln()
            return Person(name, age, cityName)
        }
    }

    private fun createJsonPerson(person: Person): String {
        // Создание файла JSON
        return """
        {
            "name": "${person.name}",
            "age": ${person.age},
            "city": "${person.city}"
        }
    """.trimIndent()
    }

    private fun createOrOpenJson() {
        // Создание / Открытие файла
        println("Введите имя файла (без расширения): ")
        val jsonFileName = readln() + ".json"

        jsonFile = File(jsonFileName)
        jsonFile.createNewFile()
        println("Файл создан / открыт: ${jsonFile.absolutePath}")
    }

    private fun writeJson() {
        jsonFile.writeText(createJsonPerson(createPerson()))
        println("JSON записан.")
    }

    private fun readJson() {
        // Чтение файла JSON
        println("Содержимое файла JSON:")
        val jsonFileContentRead = jsonFile.readText()
        println(jsonFileContentRead)
    }

    private fun deleteJson() {
        // Удаление файла JSON
        println("Файл удаляется: ${jsonFile.absolutePath}")
        jsonFile.delete()
        println("Файл удален.")
    }

}

class PartXml {
    private lateinit var xmlFile: File

    fun workWithXml() {
        var userInitChoice: Int
        while (true) {
            println(
                "Выберите действие:\n" +
                        "1. Создать файл XML / Открыть файл XML\n" +
                        "2. Записать в файл\n" +
                        "3. Прочитать файл\n" +
                        "4. Удалить файл\n" +
                        "0. Выход\n" +
                        "Введите номер:\n"
            )

            userInitChoice = try {
                readln().toInt()
            } catch (e: Exception) {
                -1
            }

            when (userInitChoice) {
                -1 -> println("Неправильный ввод.")
                0 -> break
                1 -> createOrOpenXml()
                2 -> writeXml()
                3 -> readXml()
                4 -> deleteXml()
            }
        }
    }

    private fun createXmlPerson(person: Person): String {
        // Создание файла XML вручную
        return """
        <?xml version="1.0" encoding="UTF-8"?>
        <person>
            <name>${person.name}</name>
            <age>${person.age}</age>
            <city>${person.city}</city>
        </person>
    """.trimIndent()
    }

    private fun createOrOpenXml() {
        // Создание / Открытие файла
        println("Введите имя файла (без расширения): ")
        val xmlFileName = readln() + ".xml"

        xmlFile = File(xmlFileName)
        xmlFile.createNewFile()
        println("Файл создан / открыт: ${xmlFile.absolutePath}")
    }

    private fun writeXml() {
        // Запись XML в файл
        xmlFile.writeText(createXmlPerson(PartJson.createPerson()))
    }

    private fun readXml() {
        // Чтение файла XML
        println("Содержимое файла XML:")
        val xmlFileContentRead = xmlFile.readText()
        println(xmlFileContentRead)
    }

    private fun deleteXml() {
        // Удаление файла XML
        xmlFile.delete()
    }

}

class PartZip {
    private lateinit var zipOutputStream: ZipOutputStream

    fun workWithZip() {
        var userInitChoice: Int
        while (true) {
            println(
                "Выберите действие:\n" +
                        "1. Создать / открыть архив\n" +
                        "2. Добавить файл в архив\n" +
                        "3. Разархивировать\n" +
                        "4. Удалить архив\n" +
                        "5. Удалить файл в архиве\n" +
                        "0. Выход\n" +
                        "Введите номер:\n"
            )

            userInitChoice = try {
                readln().toInt()
            } catch (e: Exception) {
                -1
            }

            when (userInitChoice) {
                -1 -> println("Неправильный ввод.")
                0 -> {
                    break
                }

                1 -> createOrOpenZip()
                2 -> addFileToZip()
                3 -> unZip()
                4 -> deleteZip()
                5 -> deleteFileInZip()
            }
        }
    }

    private fun createOrOpenZip() {
        // Создание / Открытие файла
        println("Введите имя архива (без расширения): ")
        val zipFileName = readln() + ".zip"
        zipOutputStream = ZipOutputStream(FileOutputStream(zipFileName))
    }

    private fun addFileToZip() {
        // Добавление файла в архив
        println("Введите имя файла (без расширения): ")
        val pathName = readln() + ".txt"
        val fileToAdd = File(pathName)

        try {
            zipOutputStream.putNextEntry(ZipEntry(fileToAdd.name))
            fileToAdd.inputStream().use { input ->
                zipOutputStream.buffered().use { output ->
                    input.copyTo(output)
                }
            }
            println("Новый файл добавлен.")
        } catch (e: Exception) {
            println("Не удалось добавить файл: $e")
        }

    }

    private fun closeZip() {
        // Закрытие архива
        zipOutputStream.closeEntry()
        zipOutputStream.close()
    }

    private fun unZip() {
        // Разархивирование файла и вывод данных
        println("Введите имя архива (без расширения): ")
        val zipFileName = readln() + ".zip"

        val zipFile = ZipFile(zipFileName)
        val zipEntries = zipFile.entries()
        while (zipEntries.hasMoreElements()) {
            val entry = zipEntries.nextElement()
            println("${entry.name} \t\t|| ${entry.size} байт")
            println("------------")
        }
    }

    private fun deleteZip() {
        // Удаление архива
        println("Введите имя архива (без расширения): ")
        val zipFileName = readln() + ".zip"
        File(zipFileName).delete()
    }

    private fun deleteFileInZip() {
        println("Введите имя файла (без расширения): ")
        val pathName = readln() + ".txt"
        val file = File(pathName)
        println("Удаление файла: ${file.absolutePath}")
        file.delete()
        println("Файл удален.")
    }

}


fun main() {
    var userInitChoice: Int
    while (true) {
        println(
            "Выберите действие:\n" +
                    "1. Вывести информацию о файловой системе\n" +
                    "2. Работа с файлами\n" +
                    "3. Работа с JSON\n" +
                    "4. Работа с XML\n" +
                    "5. Работа с ZIP\n" +
                    "0. Выход\n" +
                    "Введите номер:\n"
        )

        userInitChoice = try {
            readln().toInt()
        } catch (e: Exception) {
            -1
        }

        when (userInitChoice) {
            -1 -> println("Неправильный ввод.")
            0 -> break

            1 -> {
                val fileWork = PartFiles()
                fileWork.showDiskInfo()
            }

            2 -> {
                val fileWork = PartFiles()
                fileWork.workWithFiles()
            }

            3 -> {
                val jsonWork = PartJson()
                jsonWork.workWithJson()
            }

            4 -> {
                val xmlWork = PartXml()
                xmlWork.workWithXml()
            }

            5 -> {
                val zipWork = PartZip()
                zipWork.workWithZip()
            }

            else -> println("Неправильный ввод.")
        }
    }
}