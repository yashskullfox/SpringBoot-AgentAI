# Setting Up Local Agentic AI Environment with Ollama and Spring Boot

To build a strong AI app at home, need a reliable server that can run AI models. Instead of using cloud services,
you can run everything on own computer. This guide shows how to set up a local AI environment with **Ollama**,
which turns your machine into a simple, self-contained AI service you can use from your Spring Boot app.

---

## 1. Install Ollama

First, install the Ollama CLI on your machine.

### By Operating System

Use the appropriate command for your operating system:

* **macOS (Homebrew):**
    ```sh
    brew install ollama
    ```

* **Windows (PowerShell as Administrator):**
    ```powershell
    iwr -useb [https://ollama.ai/install.ps1](https://ollama.ai/install.ps1) | iex
    ```
  *After installation, restart your terminal.*

* **Linux (Shell):**
    ```sh
    curl -fsSL [https://ollama.com/install.sh](https://ollama.com/install.sh) | sh
    ```

### Verify Installation

To confirm that Ollama was installed correctly, open a new terminal and run:

```sh
ollama --version
```

If you see a version number, you're ready to move on.

## 2. Run and Verify the API Server

Ollama can be run in two modes:

* **Interactive mode (`ollama run`):** Good for quick tests directly in the terminal.
* **Server mode (`ollama serve`):** Required for integrating with your application.

Start the server by running the following command. Leave this process running in its own terminal while developing and
running your app.

```sh
ollama serve
```

## 3. Download and Use a Model

### Download a DeepSeek Model

The **DeepSeek Coder** models are powerful, open-source models that are particularly good at reasoning and code-related tasks.

Use the following command to download a model (the first download may take several minutes):

```sh
ollama pull deepseek-r1:8b
```
Choose the Right Model for Your System

Pick a model that fits your computer’s available RAM. Larger models are often more capable but require more memory and can be slow or crash on low-RAM systems.

### Use these guidelines to make a selection:

`deepseek-r1:1.5b:` ~1.1 GB disk, needs 4 GB+ RAM. Best for basic testing and low-end machines.

`deepseek-r1:7b:` ~4.1 GB disk, needs 8 GB+ RAM. A good balance for most modern laptops.

`deepseek-r1:32b:` ~18 GB disk, needs 32 GB+ RAM. For powerful desktops with stronger reasoning.

`deepseek-r1:671b:` ~404 GB disk, needs 512 GB+ RAM. Requires specialized hardware.

This spring boot application uses deepseek-r1:8b in the examples, but if your machine has 8 GB of RAM or more, deepseek-r1:7b is a solid choice.

By default, the API server listens at http://localhost:11434.

You can test the API with a curl request to ensure it's working:

```sh
    curl http://localhost:11434/api/chat \
      -H "Content-Type: application/json" \
        -d '{
              "model": "deepseek-r1:8b",
              "messages": [
            {
            "role": "user",
            "content": "how are you?"
            }
          ],
          "stream": false
        }'
```
If the command returns a JSON response with the model’s answer, your local AI backend is working and ready for Spring Boot.

Once the curl test works, you’re ready to connect given Spring Boot app to your new local AI service, which exposed a small POST endpoint to take request and provide response based on it.

### Running and Interacting with Your API
The Spring Boot application can be launched directly from an IDE or via the Maven wrapper from the command line.

Navigate to the project's root directory in a terminal and execute:

```sh
mvn spring-boot:run
```
Upon successful startup, the console will indicate that the application is running on port 8080.   

To test the API endpoint, a curl command can be used to send a POST request.

```sh
curl -X POST http://localhost:8080/api/v1/ai/chat \
-H "Content-Type: text/plain" \
-d "Explain the concept of dependency injection in Spring Boot in simple terms."
```
