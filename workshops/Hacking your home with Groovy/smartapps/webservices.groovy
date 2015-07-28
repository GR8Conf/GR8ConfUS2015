/**
 *  Web Service Tutorial
 *
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 * 	  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
		name: "Web Service Tutorial",
		namespace: "mrnohr",
		author: "Matt Nohr",
		description: "Example web services tutorial",
		category: "My Apps",
		iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
		iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
		iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Allow external service to control these things...") {
		input "switches", "capability.switch", multiple: true, required: true
	}
}

mappings {
	path("/switches") {
		action:
		[
				GET: "listSwitches"
		]
	}
	path("/switches/:command") {
		action:
		[
				PUT: "updateSwitches"
		]
	}
}

def installed() {}

def updated() {}

def listSwitches() {
	def resp = []
	switches.each {
		resp << [name: it.displayName, value: it.currentValue("switch")]
	}
	return resp
}

void updateSwitches() {
	def command = params.command

	if(command) {
		switches.each {
			if(!it.hasCommand(command)) {
				httpError(501, "$command is not a valid command for all switches specified")
			}
		}

		switches."$command"()
	}
}

/*
curl -H "Authorization: Bearer <api token>" <api endpoint>/switches
curl -H "Authorization: Bearer <api token>" -X PUT <api endpoint>/switches/on
*/
