import os
import socket
import threading

IP =socket.gethostbyname(socket.gethostname())
PORT = 4466
ADDR = (IP,PORT)
SIZE =1024
FORMAT = "utf-8"
SERVER_DATA_PATH = "server_data"

"""
CMD@Msg
"""

def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    conn.send("OK@Welcome to the File Server.".encode(FORMAT))
    
    
    while True:
        data = conn.recv(SIZE).decode(FORMAT)
        data = data.split("@")
        cmd = data[0]
        
        if cmd == "HELP":
           send_data = "OK@"
           send_data = "create <filename.txt> : CREATE THE FILE.\n"
           send_data = "cat <filename.txt>    : READ THE FILE.\n"
           send_data = "edit <filename.txt>   : EDIT THE FILE.\n"
           send_data = "delete <filename.txt> : DELETE THE FILE.\n"
           send_data = "EXIT"
           send_data = "HELP:LIST ALL THE COMMANDS\n"       
           
           conn.send(send_data.encode(FORMAT))
           
        elif cmd == "EXIT":
             break
             
        elif cmd == "create":
             pass
             
        elif cmd == "cat":
             pass   
             
        elif cmd == "edit":
             pass 
             
        elif cmd == "delete":
             pass           
           
           

def main():
    print("[STARTING]server is starting.....")
    server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    server.bind(ADDR)
    server.listen()
    print("[LISTENING]server is listening.....")
    
    
    while True:
         conn, addr = server.accept()
         thread = threading.Thread(target=handle_client, args=(conn, addr))
         thread.start()

if __name__ == "__main__":
    main()
