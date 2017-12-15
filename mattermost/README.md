# Mattermost - Open source, private cloud Slack-alternative

Homepage: https://about.mattermost.com/

Download for community / Team Edition: https://about.mattermost.com/download/

## Mattermost start
```bash
docker run --name mattermost-preview -d --publish 8065:8065 mattermost/mattermost-preview
```

Open http://localhost:8065/, create user, log to the server, create team, share team invite link (Main Menu -> Get Team Invite Link). 

System management via URL http://localhost:8065/admin_console or Main Menu -> System Console

## Mattermost - what I like
 * Easy to start with
 * Intuitive UI
 * Nice system console / settings
 * Mobile & Desktop apps for main platforms
 * Text formatting (e.g. tables), many icons
 * Pinned posts, recent mentions, flagged posts, permalink
 * Group chats without need to create dedicated channels

## Mattermost - custom slash command
First switch to System Console -> Settings -> Integrations -> Custom Integrations and switch Enable Custom Slash Commands option to true. Available commands are described in https://docs.mattermost.com/developer/slash-commands.html

Switch back to your team view, go to Main Menu -> Integrations -> Slash commands.

I chose poll app https://github.com/kaakaa/matterpoll-emoji so you can follow according the README.md

My config is:
```json
{
  "host": "http://172.17.0.2:8065",
  "listen": "172.17.0.1:8505",
  "token": "an8dgshomtgwzcfxeskyoxcxch",
  "user": {
    "id": "bot",
    "password": "botbot"
  }
}
```

As I use Mattermost in docker and running matterpoll-emoji from local machine I had to figure out IP address of the docker instance and my local IP address on docker interface.
```bash
docker exec mattermost-preview ip address
telnet 172.17.0.2 8065
ip address | grep 172.17
```

And I also had to build matterpoll-emoji from master as the Mattermost server is in version 4.5 and the  pre compiled release was not working properly with it.

If custom `/poll` command is not working and in System Console -> Logs you see something like `Command with a trigger of 'poll' failed [details: Post http://172.17.0.1:8505/poll: address forbidden]`, you need to go to System Console -> Advanced -> Developer and modify Allow untrusted internal connections to: section. I have it set to `172.17.0.1 127.0.0.1 172.17.0.2`

And now you can finally try it: `/poll "What do you guys wanna grab for lunch?" :pizza: :sushi: :fried_shrimp: :spaghetti:`