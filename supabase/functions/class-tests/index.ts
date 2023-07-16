// Follow this setup guide to integrate the Deno language server with your editor:
// https://deno.land/manual/getting_started/setup_your_environment
// This enables autocomplete, go to definition, etc.

import { serve } from "https://deno.land/std@0.168.0/http/server.ts"
import { DOMParser, Element, Node } from "https://deno.land/x/deno_dom/deno-dom-wasm.ts";

console.log("Hello from Functions!")
const session_url = "https://orga.kepi.de/gfs/ajax/dologin.php"
const exam_url = "https://orga.kepi.de/gfs/gfsanmelden.php"

serve(async (req) => {
  const url = new URL(req.url)
  const username = url.searchParams.get("username")!!
  const password = url.searchParams.get("password")!!

  const formData = new FormData()
  formData.append("username", username)
  formData.append("password", password)

  const sessionResponse = await fetch(session_url, {
    method: 'POST',
    body: formData
  })

  const cookie = sessionResponse.headers.get("set-cookie")!!

  const examResponse = await fetch(exam_url, {
    method: 'GET',
    headers: {
        "Cookie": cookie
    }
  })

  const parser = new DOMParser();
  const document = parser.parseFromString(await examResponse.text(), "text/html")!;
  const page1 = document.getElementById("page1")!;
  const divlist = page1.getElementById("divlist")!;
  const trElements = divlist.querySelectorAll("tr");

  const examItems = Array.from(trElements)
      .slice(1)
      .filter((element: Node) => element.nodeName.toLowerCase() === "tr")
      .map((element: Node) => {
        const string = element.textContent!;
        const dateStr = string.slice(0, 10);
        const subject = string.slice(10);
        console.log(string)
        return {
            date: dateStr,
            subject: subject,
        }
      });

  return new Response(
    JSON.stringify(examItems),
    { headers: { "Content-Type": "application/json" } },
  )
})

// To invoke:
// curl -i --location --request POST 'http://localhost:54321/functions/v1/' \
//   --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZS1kZW1vIiwicm9sZSI6ImFub24ifQ.625_WdcF3KHqz5amU0x2X5WWHP-OEs_4qj0ssLNHzTs' \
//   --header 'Content-Type: application/json' \
//   --data '{"name":"Functions"}'
