package org.mmpp.twitter;


/**
 * ボロットサービス
 * @author kou
 *
 */
public class RobotServiceImpl implements RobotService{

	private final static String START="start";
	private final static String STOP="stop";
	private String _status="";
	private static RobotServiceProcess _process = null;
	private static java.util.concurrent.ExecutorService _executorService = null;
	private static java.util.concurrent.ExecutorService getExecutorService(){
		if(_executorService == null){
			_executorService = java.util.concurrent.Executors.newSingleThreadExecutor();
		}
		return _executorService;
	}
	
	private static void setExecutorService(java.util.concurrent.ExecutorService executorService){
		_executorService = executorService;
	}
	public static RobotServiceProcess getRobotServiceProcess(){
		if(_process == null){
			_process = new RobotServiceProcess();
		}
		return _process;
	}
	private void setStatus(String status){
		_status = status;
	}
	@Override
	public void start() {
		if(getStatus().equals(START)){
			return;
		}
		setStatus(START);
		RobotServiceProcess process = getRobotServiceProcess();
		getExecutorService().execute(process);
	}

	@Override
	public void stop() {
		getExecutorService().shutdown();
		while(!getExecutorService().isShutdown()){
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
			}
		}
		setExecutorService(null);
		setStatus(STOP);
	}

	@Override
	public String getStatus() {
		return _status;
	}
}
