#top -l 1 | grep 'CPU usage'

top -bn1 | grep -e 'Cpu(s)' -e 'Mem'; iostat -dx 1 2 | grep xvda | tail -1