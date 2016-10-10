// Consolidates and encapsulates CreateAuto, UpdateAuto, and ProxyAuto

package adapter;

import server.AutoServer;

public final class BuildAuto extends ProxyAuto implements CreateAuto, UpdateAuto, FixAuto, EditThread, AutoServer{
	
}