# PIM MCP Server

A Spring Boot MCP (Model Context Protocol) server that provides catalog management tools for creating product entries in
Contentful CMS.

## Features

- **create_catalog_entry**: Creates new catalog entries in Contentful with product information
- Supports Apple and Dell brands
- Integrates with Contentful Management API

## Configuration

### Required Environment Variables

Set the following properties in `application.properties`:

```properties
contentful.url=https://api.contentful.com
contentful.key=YOUR_CONTENTFUL_ACCESS_TOKEN
contentful.space=YOUR_CONTENTFUL_SPACE_ID
```

### Claude Desktop Integration

Add to your Claude Desktop config (`~/Library/Application Support/Claude/claude_desktop_config.json`):

```json
{
  "mcpServers": {
    "catalog": {
      "command": "java",
      "args": [
        "-jar",
        "/path/to/pim-mcp/build/libs/pim-mcp-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

## Build & Run

```bash
# Build the project
./gradlew build

# The JAR will be created at: build/libs/pim-mcp-0.0.1-SNAPSHOT.jar
```

## Testing

```bash
./gradlew test
```

## Usage with Claude

Once configured, you can ask Claude to:

- "Create a catalog entry for iPhone 15 with 256GB storage"
- "Add a Dell XPS laptop to the catalog"

The MCP server will handle the Contentful API integration automatically.
