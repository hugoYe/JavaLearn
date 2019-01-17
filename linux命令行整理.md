linux常规操作命令行整理
=============
## 压缩、解压
tar -cvf fille.tar file（可以多个文件空格隔开）-c: 建立压缩档案；-v: 显示所有过程；-f: 使用档案名字，是必须的，是最后一个参数）     
tar -xvf file.tar 解包到当前目录    
tar -xvf file.tar -C dir 把文件解压到指定目录中    
zip 压缩后文件名 源文件    
zip -r 压缩后目录名 原目录    
unzip file.zip -d dir 解压到指定目录    
gunzip file1.gz 解压 file1.gz     
gzip file1 压缩 file1    
gzip -9 file 最大程度压缩文件    

## 文件、目录
rm -f file 强制删除文件，不提示     
rm -r dir 递归删除其文件和文件夹     
rm -rf dir 强制删除文件夹及其内容，不提示     
mv dir/file dir 将文件或者文件夹移动到指定目录    
mv -t dir file 将文件或者文件夹移动到指定目录    
mkdir dir dir2 创建两个文件夹    
mkdir -p /tmp/dir 创建多级目录    
cp file file1 将文件file复制一份file1    
cp -a file/dir dir 将文件或者文件夹复制到指定目录    
cd .. 返回上一级目录    
cd ../.. 返回上两级目录    
cd / 返回根目录    
ls 列举出当前目录中所有文件    
ls -a 列举出当前目录中所有文件，包括隐藏文件    
ls -l 显示文件的详细信息    
ls -lrt 按时时间排序显示文件    
pwd 显示当前路径    

## 网络相关
ip add 显示当前ip地址     
ifdown eth0 禁用 ‘eth0’ 网络设备    
ifup eth0 启用 ‘eth0’ 网络设备     

## 系统相关
su 用户名 切换用户登录    
shutdown -h now 关机    
shutdown -r now 重启    
reboot 重启   
