const express = require('express');
const { OpenAI } = require('openai');
require('dotenv').config();
require('./eureka-client');

// Initialisation de l'application backend en utilisant express framework
const app = express();

// Import of the PORT from .env file (if not found use 5000 by default)
const port = process.env.PORT || 5000;

// Initialization of the openai instance using the secret key imported from .env file
const openai = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY
});

app.use(express.json());

// endpoint using POST method, example of use
// POST : http://localhost:5000/api/generate
// Body: { "prompt": "say hello to me !" }
app.post('/api/generate', async (req, res) => {
  const { prompt } = req.body;

  try {
    const completion = await openai.chat.completions.create({
      model: "gpt-3.5-turbo", // or "gpt-3.5-turbo"
      messages: [{ role: "user", content: prompt }]
    });

    res.json({ response: completion.choices[0].message.content });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Something went wrong" });
  }
});

// Listen to any call from a certain port
app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
